package online.cccccc.repair.business.repair.test;
import java.util.Date;

import online.cccccc.repair.business.repair.BussinessRepairApplication;
import online.cccccc.repair.business.repair.service.TRepairService;
import online.cccccc.repair.commons.domain.TRepair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 你是电脑
 * @create 2019/10/30 - 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BussinessRepairApplication.class)
public class TRepairServiceTest {

    @Resource
    public TRepairService tRepairService;

    @Test
    public void testInsert(){
        TRepair tRepair = new TRepair();
        tRepair.setRepairRegion((byte)0);
        tRepair.setRepairDormitory("2#404");
        tRepair.setRepairContact("18379790727");
        tRepair.setRepairNumber(0);
        tRepair.setRepairDescription("需要重装系统");
        tRepair.setRepairDate(new Date());

        tRepairService.insert(tRepair);
    }
}
