package online.cccccc.repair.business.repair.config;

import online.cccccc.repair.business.repair.BusinessRepairApplication;
import online.cccccc.repair.business.repair.annotation.CqFeginService;
import online.cccccc.repair.business.repair.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 你是电脑
 * @create 2019/11/10 - 9:39
 */
@Component
public class BeanConfig {
    private List<String> classNames = new ArrayList<>();
    @Autowired
    private ApplicationContext applicationContext;
    @Bean
    public FeginProxyClass feginProxyClass(){
        return new FeginProxyClass();
    }
    @Autowired
    private FeginProxyClass feginProxyClass;

    private Object getProxyInstance() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(FeignService.class);
        enhancer.setCallback(feginProxyClass);
        return enhancer.create();
    }

    @Bean
    public FeignService feignService() {
//        applicationContext
        return (FeignService) getProxyInstance();
    }


    /**
     * 扫描 class
     * @param packageName
     */
    private void doScanner(String packageName){
        URL url = BeanConfig.class.getClassLoader().getResource( packageName.replaceAll("\\.", "/"));
        assert url != null;
        File classDir = new File(url.getFile());
        for (File file : Objects.requireNonNull(classDir.listFiles())) {
            if (file.isDirectory()) {
                doScanner(packageName+"."+file.getName());
            }else if(packageName.endsWith("service")){
                classNames.add(packageName+"."+file.getName().replaceAll(".class",""));
            }
        }
    }
}
