package online.cccccc.repair.business.repair.service.impl;

import online.cccccc.repair.commons.domain.TMail;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import online.cccccc.repair.business.repair.mapper.TMailMapper;
import online.cccccc.repair.business.repair.service.TMailService;
/**
@create 2019/10/30 - 16:20
@author    你是电脑
*/
@Service
public class TMailServiceImpl implements TMailService{

    @Resource
    private TMailMapper tMailMapper;

    @Override
    public int insert(TMail tMail) {
        return tMailMapper.insert(tMail);
    }
}
