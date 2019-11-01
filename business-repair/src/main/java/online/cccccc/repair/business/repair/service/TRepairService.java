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
     * @param repair
     * @return 返回单号
     */
    int insert(TRepair repair);

    /**
     * 根据 ID / 单号 查订单详情
     * @param id
     * @return
     */
    TRepair getRepairbyId(String id);

    List<TRepair> selectAll();

    /**
     * 根据主键根新
     * @param id 主键
     * @return
     */
    int updataById(String id);
}
