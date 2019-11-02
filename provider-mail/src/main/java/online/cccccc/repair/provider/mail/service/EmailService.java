package online.cccccc.repair.provider.mail.service;

import online.cccccc.repair.commons.domain.TRepair;
import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.service.RedisService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    public static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisService redisService;

    @Value("${business.host}")
    private String businessHost;

    @Async
    public void receive(List<EmailDTO> emailDtos) {
        emailDtos.forEach(emailDTO -> {
            try {
                // 发送 HTML 模板邮件
                TRepair repair = (TRepair) redisService.get(emailDTO.getToken());
                if (repair!=null){
                    Context context = new Context();
                    //单号
                    context.setVariable("repairId",repair.getRepairId()) ;
                    //校区 0 老校区 1 新校区
                    context.setVariable("repairRegion",repair.getRepairRegion()==0?"老校区":"新校区") ;
                    //宿舍
                    context.setVariable("repairDormitory",repair.getRepairDormitory()) ;
                    //联系方式
                    context.setVariable("repairContact",repair.getRepairContact()) ;
                    //原因
                    context.setVariable("repairDescription",repair.getRepairDescription()) ;
                    //下单时间
                    context.setVariable("repairDate", repair.getRepairDate()) ;
                    context.setVariable("businessHost", businessHost) ;
                    context.setVariable("token",emailDTO.getToken()) ;
                    String emailTemplate = templateEngine.process("index", context);
                    logger.info("给 {} 发送了邮件 ，订单 {} ---------->{}",emailDTO.getTMails().getMailName(),repair.getRepairId(),DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd hh:mm:ss"));
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
            String property = applicationContext.getEnvironment().getProperty("spring.mail.username");
            assert property != null;
            helper.setFrom(property);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(message);
        } catch (Exception e) {

        }
    }
}
