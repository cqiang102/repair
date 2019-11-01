package online.cccccc.repair.commons.dto;

import lombok.Data;
import online.cccccc.repair.commons.domain.TMail;
import online.cccccc.repair.commons.domain.TRepair;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 17:30
 */
@Data
public class EmailDTO {
    private TMail tMails;
    private String token;
}
