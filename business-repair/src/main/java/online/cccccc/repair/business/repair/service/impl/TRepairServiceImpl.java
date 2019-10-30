package online.cccccc.repair.business.repair.service.impl;

import online.cccccc.repair.commons.domain.TRepair;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import online.cccccc.repair.business.repair.mapper.TRepairMapper;
import online.cccccc.repair.business.repair.service.TRepairService;
/**
@create 2019/10/30 - 16:20
@author    你是电脑
*/
@Service
public class TRepairServiceImpl implements TRepairService{

    @Resource
    private TRepairMapper tRepairMapper;

    @Override
    public int insert(TRepair repair) {
        return tRepairMapper.insert(repair);
    }
}
