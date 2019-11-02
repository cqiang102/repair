package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.commons.domain.TMail;

import java.util.List;

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

    /**
     * 查询所有收件人
     * @return
     */
    List<TMail> selectAll();

    /**
     * 删除收件人
     * @param mail 邮箱账号
     * @return
     */
    int delete(String mail);

    /**
     * 根据邮箱查收件人
     * @param mail
     * @return
     */
    TMail selectOneByMail(String mail);


}
