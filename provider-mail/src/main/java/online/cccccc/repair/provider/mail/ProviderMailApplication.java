package online.cccccc.repair.provider.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 你是电脑
 * @create 2019/11/1 - 17:14
 */
@SpringBootApplication(scanBasePackages = "online.cccccc.repair")
@EnableAsync
@ComponentScan(basePackages = "online.cccccc.repair")
public class ProviderMailApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMailApplication.class,args);
    }
}
