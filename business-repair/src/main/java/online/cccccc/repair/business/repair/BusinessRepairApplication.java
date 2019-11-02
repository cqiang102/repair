package online.cccccc.repair.business.repair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 你是电脑
 * @create 2019/10/30 - 16:16
 */
@SpringBootApplication(scanBasePackages = "online.cccccc.repair")
@MapperScan("online.cccccc.repair.business.repair.mapper")
@EnableAspectJAutoProxy
@EnableAsync
public class BusinessRepairApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessRepairApplication.class,args);
    }
}
