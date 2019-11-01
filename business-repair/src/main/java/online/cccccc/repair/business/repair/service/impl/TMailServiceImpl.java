package online.cccccc.repair.business.repair.service.impl;

import online.cccccc.repair.commons.domain.TMail;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import online.cccccc.repair.business.repair.mapper.TMailMapper;
import online.cccccc.repair.business.repair.service.TMailService;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

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
        tMail.setMailDate(new Date());
        return tMailMapper.insert(tMail);
    }

    @Override
    public List<TMail> selectAll() {
        return tMailMapper.selectAll();
    }

    @Override
    public int delete(String mail) {
        Example example = new Example(TMail.class);
        example.createCriteria().andEqualTo("mailMail",mail);
        return tMailMapper.deleteByExample(example);
    }

    @Override
    public TMail selectOneByMail(String mail) {
        TMail tMail = new TMail();
        tMail.setMailMail(mail);
        return tMailMapper.selectOne(tMail);
    }
}
