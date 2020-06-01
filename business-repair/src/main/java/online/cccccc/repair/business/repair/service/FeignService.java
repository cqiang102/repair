package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.business.repair.annotation.CqFeginService;
import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.dto.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 你是电脑
 * @date 2019/11/10 - 9:36
 */
@CqFeginService("localhost:82")
public interface FeignService {
    /**
     * 发送邮件
     * @param emailDtos 邮件 DTO {@link EmailDTO}
     * @return 请求成功响应 {@link Result}
     */
    @PostMapping("email")
    Result sendEmail(@RequestBody List<EmailDTO> emailDtos);

    /**
     * 测试发送
     * @param test 测试
     * @return 测试
     */
    @PostMapping("test")
    Result test(@RequestBody String test);
}
