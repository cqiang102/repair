package online.cccccc.repair.business.repair.controller;

import online.cccccc.repair.business.repair.service.FeignService;
import online.cccccc.repair.business.repair.service.TMailService;
import online.cccccc.repair.commons.domain.TMail;
import online.cccccc.repair.commons.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 你是电脑
 * @date 2019/11/1 - 16:31
 */
@RestController
@CrossOrigin("*")
public class MailController {
    public static final Logger logger = LoggerFactory.getLogger(MailController.class);
    @Resource
    private TMailService tMailService;
    @Resource
    private FeignService feignService;
    @GetMapping("mails")
    public Result mails(){
        List<TMail> tMails = tMailService.selectAll();
        feignService.test("test");
        return Result.makeResult(HttpStatus.OK.value(),"查询成功",tMails);
    }
    @DeleteMapping("mail/{mail}")
    public Result delete(@PathVariable String mail){
        int delete = tMailService.delete(mail);
        if (delete>0){
        return Result.makeResult(HttpStatus.OK.value(),"删除成功",delete);
        }
        return Result.makeResult(HttpStatus.OK.value(),"删除失败",null);
    }
    @GetMapping("mail/{mail}")
    public Result selectOne(@PathVariable String mail){
        TMail tMail = tMailService.selectOneByMail(mail);
        return Result.makeResult(HttpStatus.OK.value(),"查询成功",tMail);
    }
    @PostMapping("mail")
    public Result insert(@RequestBody TMail tMail){
        int insert = tMailService.insert(tMail);
        if (insert>0){
        return Result.makeResult(HttpStatus.OK.value(),"提交成功",tMail);
        }
        return Result.makeResult(HttpStatus.OK.value(),"提交失败",null);
    }
}
