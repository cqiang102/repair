package online.cccccc.repair.business.repair.service.impl;

import online.cccccc.repair.business.repair.mapper.TRepairMapper;
import online.cccccc.repair.business.repair.service.TMailService;
import online.cccccc.repair.business.repair.service.TRepairService;
import online.cccccc.repair.business.repair.utils.HttpClientUtils;
import online.cccccc.repair.commons.domain.TMail;
import online.cccccc.repair.commons.domain.TRepair;
import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.service.RedisService;
import online.cccccc.repair.commons.utils.MapperUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
@create 2019/10/30 - 16:20
@author    你是电脑
*/
@Service
public class TRepairServiceImpl implements TRepairService{

    @Resource
    private TRepairMapper tRepairMapper;
    @Resource
    private TMailService tMailService;

    @Resource
    private RedisService redisService;

    @Resource
    private HttpClientUtils httpClientUtils;

    @Value("${provider.host}")
    private String providerHost;

    @Override

    public int insert(TRepair repair) {
        repair.setRepairDate(new Date());
        repair.setRepairNumber(0);
        int insert = 0;
        try {
            insert = tRepairMapper.insert(repair);
        } catch (Exception e) {
            System.out.println("error");
        }

        if (insert >0) {
            //TODO 从 tMailService 取得收件人 调用发邮件 provider
            //生成 UUID 存入 redis ,发送出去的邮件末尾带 UUID,点击后删除 redis 中的 UUID
            List<TMail> tMails = tMailService.selectAll();
            //封住成传输对象
            List<EmailDTO> emailDtos = new LinkedList<>();

            tMails.forEach(tMail -> {
                EmailDTO emailDTO = new EmailDTO();
                emailDTO.setTMails(tMail);
                String uuid = UUID.randomUUID().toString();
                redisService.put(uuid,repair);
                emailDTO.setToken(uuid);
                emailDtos.add(emailDTO);
            });
            sendEmail(emailDtos);


        }
        return insert;
    }


    @Override
    public TRepair getRepairbyId(String id) {

        return tRepairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TRepair> selectAll() {
        return tRepairMapper.selectAll();
    }

    @Override
    public int updataById(String id) {
        TRepair repair = tRepairMapper.selectByPrimaryKey(id);
        if(repair == null){
            return 0;
        }
        repair.setRepairNumber(repair.getRepairNumber()+1);

        return tRepairMapper.updateByPrimaryKey(repair);
    }
    @Async
    protected void sendEmail(List<EmailDTO> emailDtos){
        //TODO HTTP 方式请求提供者,发送邮件
        try {
            CloseableHttpResponse post = httpClientUtils.post("http://"+providerHost+"/email", MapperUtils.obj2json(emailDtos));
            HttpEntity entity = post.getEntity();
            String string = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
