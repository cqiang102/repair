package online.cccccc.repair.business.repair.service;

import online.cccccc.repair.commons.domain.TRepair;

import java.util.List;

/**
@create 2019/10/30 - 16:20
@author    你是电脑
*/
public interface TRepairService{
    /**
     * 添加数据
     * @param repair {@link TRepair}
     * @return 返回单号
     */
    int insert(TRepair repair);

    /**
     * 根据 ID / 单号 查订单详情
     * @param id ID / 单号
     * @return {@link TRepair}
     */
    TRepair getRepairbyId(String id);

    /**
     * 查询所有报修单
     * @return {@link TRepair}
     */
    List<TRepair> selectAll();

    /**
     * 根据主键根新
     * @param id 主键
     * @return 受影响行数
     */
    int updateById(String id);
}
