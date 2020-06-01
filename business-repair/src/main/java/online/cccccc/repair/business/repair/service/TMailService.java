package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.commons.domain.TMail;

import java.util.List;

/**
 * @author 你是电脑
 * @date 2019/10/30 - 16:20
 */
public interface TMailService {
    /**
     * 添加收邮件管理员
     * @param tMail {@link TMail}
     * @return 受影响行数
     */
    int insert(TMail tMail);

    /**
     * 查询所有收件人
     * @return {@link TMail}
     */
    List<TMail> selectAll();

    /**
     * 删除收件人
     * @param mail 邮箱账号
     * @return 受影响行数
     */
    int delete(String mail);

    /**
     * 根据邮箱查收件人
     * @param mail 邮箱账号
     * @return 受影响行数
     */
    TMail selectOneByMail(String mail);


}
