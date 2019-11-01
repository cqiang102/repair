package online.cccccc.repair.provider.mail.controller;

import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.dto.Result;
import online.cccccc.repair.commons.service.RedisService;
import online.cccccc.repair.provider.mail.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 17:18
 */
@CrossOrigin("*")
@RestController
@RequestMapping("email")
public class EmailController {

    @Resource
    private EmailService emailService;

    @Resource
    private RedisService redisService;

    @PostMapping("")
    public Result sendEmail(@RequestBody List<EmailDTO> emailDtos){
        emailDtos.forEach(emailDTO -> {
            //发送
            System.out.println(emailDTO);
        });
        return Result.makeResult(HttpStatus.OK.value(),"ok",null);
    }
}
