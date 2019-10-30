package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.commons.domain.TMail;

/**
 * @author 你是电脑
 * @create 2019/10/30 - 16:20
 */
public interface TMailService {
    /**
     * 添加收邮件管理员
     * @param tMail
     * @return
     */
    int insert(TMail tMail);

}
