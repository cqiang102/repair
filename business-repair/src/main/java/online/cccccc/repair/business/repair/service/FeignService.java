package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.business.repair.annotation.CqFeginService;
import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.dto.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 你是电脑
 * @create 2019/11/10 - 9:36
 */

@CqFeginService
public interface FeignService {
    @PostMapping("email")
    Result sendEmail(@RequestBody List<EmailDTO> emailDtos);
    @PostMapping("test")
    Result test(@RequestBody String test);
}
