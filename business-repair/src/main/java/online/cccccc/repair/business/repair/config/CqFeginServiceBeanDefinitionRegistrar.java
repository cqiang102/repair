package online.cccccc.repair.business.repair.config;

import online.cccccc.repair.business.repair.annotation.CqFeginService;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author lacia
 * @date 2020/5/22 - 16:36
 */
public class CqFeginServiceBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private final Set<Class<?>> setClass = new LinkedHashSet<>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String applicationName = ((StandardAnnotationMetadata) importingClassMetadata).getIntrospectedClass().getName();
        ClassPathCqFeginScanner scanner = new ClassPathCqFeginScanner(registry);
        scanner.registerFilters();
        scanner.doScan(applicationName);
    }

    private void doScanner(String packageName) {
        ClassLoader classLoader = CqFeginServiceBeanDefinitionRegistrar.class.getClassLoader();
        String packageNameReplaceAll = packageName.replaceAll("\\.", "/");
        URL url = classLoader.getResource(packageNameReplaceAll);
        assert url != null;
        File classDir = new File(url.getFile());
        for (File file : Objects.requireNonNull(classDir.listFiles())) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else if (packageName.endsWith("service")) {
                try {
                    Class<?> aClass = Class.forName(packageName + "." + file.getName().replaceAll(".class", ""));
                    CqFeginService annotation = aClass.getAnnotation(CqFeginService.class);
                    if (annotation != null) {
                        setClass.add(aClass);
                    }
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
    }
}
