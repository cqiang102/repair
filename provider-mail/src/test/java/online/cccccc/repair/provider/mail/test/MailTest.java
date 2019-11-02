package online.cccccc.repair.provider.mail.test;
import java.util.Date;
import online.cccccc.repair.commons.domain.TMail;

import online.cccccc.repair.commons.dto.EmailDTO;
import online.cccccc.repair.commons.utils.MapperUtils;
import online.cccccc.repair.provider.mail.ProviderMailApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 20:49
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ProviderMailApplication.class)
public class MailTest {

//    @Test
    public void testJson(){
        List<EmailDTO> emailDTOS = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            EmailDTO emailDTO = new EmailDTO();
            TMail tMail = new TMail();
            tMail.setMailId(0);
            tMail.setMailName("name"+i);
            tMail.setMailMail("mail"+i);
            tMail.setMailDate(new Date());

            emailDTO.setTMails(tMail);
            emailDTO.setToken(i+"///"+ UUID.randomUUID().toString());

            emailDTOS.add(emailDTO);
        }
        try {
            System.out.println(MapperUtils.obj2json(emailDTOS));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
