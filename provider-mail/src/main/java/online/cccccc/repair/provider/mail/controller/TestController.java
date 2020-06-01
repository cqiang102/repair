package online.cccccc.repair.provider.mail.controller;

import online.cccccc.repair.commons.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 你是电脑
 * @date 2019/11/12 - 17:25
 */
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping
    public Result test(@RequestBody String test){
        return Result.makeResult(HttpStatus.OK.value(),"发送成功"+test,null);
    }
}
