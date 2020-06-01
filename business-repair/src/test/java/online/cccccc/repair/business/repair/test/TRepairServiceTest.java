package online.cccccc.repair.business.repair.test;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import online.cccccc.repair.business.repair.BusinessRepairApplication;
import online.cccccc.repair.business.repair.service.TRepairService;
import online.cccccc.repair.commons.domain.TRepair;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 你是电脑
 * @create 2019/10/30 - 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessRepairApplication.class)
public class TRepairServiceTest {

//    @Resource
    public TRepairService tRepairService;

//    @Test
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
    @Test
    public void testFeign(){
        String[] strings = BusinessRepairApplication.class.getAnnotation(SpringBootApplication.class).scanBasePackages();
        if (strings.length > 0 ){
            for (String string : strings) {
                System.out.println(string);
                String path = BusinessRepairApplication.class.getClassLoader().getResource("").getPath();
                System.out.println(path);
                String temp = string.replaceAll("\\.", "/");
                System.out.println(temp);
                URL resource = BusinessRepairApplication.class.getClassLoader().getResource(temp);
                File file = new File(resource.getFile());
                File serviceFile = getServiceFile(file);
                System.out.println(serviceFile);
            }

//            System.out.println(service);
//            File  file = new File(service.getFile());
//            System.out.println(file);
        }

    }

    private File getServiceFile(File file){
        if (file.isDirectory()) {
            ArrayList<File> files = Lists.newArrayList(file.listFiles());
            assert files != null;
            for (File file1 : files) {
                System.out.println(file1.getName());
                if ("service".equals(file1.getName())){
                    return file1;
                }else {
                    getServiceFile(file1);
                }
            }
        }
        return null;
    }
}
