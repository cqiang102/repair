package online.cccccc.repair.provider.mail.service;

import online.cccccc.repair.commons.domain.TRepair;
import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.service.RedisService;
import online.cccccc.repair.commons.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 17:16
 */
@Service
public class EmailService {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisService redisService;

    @Async
    public void receive(List<EmailDTO> emailDtos) {
        emailDtos.forEach(emailDTO -> {
            try {
                // 发送 HTML 模板邮件
                TRepair repair = (TRepair) redisService.get(emailDTO.getToken());
                if (repair!=null){
                    Context context = new Context();
                    context.setVariable("repairId",repair.getRepairId()) ;
                    String emailTemplate = templateEngine.process("index", context);
                    sendTemplateEmail("支付宝到账100万元", emailTemplate, emailDTO.getTMails().getMailMail());
                }else {
                    System.out.println("无效token");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * 发送普通邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendEmail(String subject, String body, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    /**
     * 发送 HTML 模板邮件
     * @param subject
     * @param body
     * @param to
     */
    @Async
    public void sendTemplateEmail(String subject, String body, String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(applicationContext.getEnvironment().getProperty("spring.mail.username"));
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (Exception e) {

        }
    }
}
