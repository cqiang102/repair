package online.cccccc.repair.business.repair.annotation;

import online.cccccc.repair.business.repair.config.CqFeginServiceBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author lacia
 * @date 2020/5/22 - 19:15
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CqFeginServiceBeanDefinitionRegistrar.class)
public @interface EnableCqFegin {
}
