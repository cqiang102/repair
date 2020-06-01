package online.cccccc.repair.provider.mail.controller;

import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.dto.Result;
import online.cccccc.repair.provider.mail.service.EmailService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @date 2019/11/1 - 17:18
 */
@CrossOrigin("*")
@RestController
@RequestMapping("email")
public class EmailController {
    public static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Resource
    private EmailService emailService;

    @PostMapping("")
    public Result sendEmail(@RequestBody List<EmailDTO> emailDtos){
        logger.info("调用了发邮件接口 ---------->{}", DateFormatUtils.format(System.currentTimeMillis(),"yyyy-MM-dd hh:mm:ss"));
        emailService.receive(emailDtos);
        return Result.makeResult(HttpStatus.OK.value(),"ok",null);
    }

}
