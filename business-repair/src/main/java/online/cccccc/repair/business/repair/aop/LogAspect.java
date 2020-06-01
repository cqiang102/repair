package online.cccccc.repair.business.repair.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author 你是电脑
 * @date 2019/11/2 - 20:18
 */
@Aspect
@Component
public class LogAspect {

    private final String pointcut = "pointcut()";


    @Pointcut("execution(* online.cccccc.repair.business.repair.controller.*.*(..))")
    private void pointcut(){}

    /**
     * 环绕通知
     * @param joinPoint 切入点 {@link ProceedingJoinPoint}
     * @return 函数返回
     * @throws Throwable
     */
    @Around(pointcut)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取logger
        Class<?> clazz = joinPoint.getTarget().getClass();
        Logger logger;
        try {
            Field field = clazz.getDeclaredField("logger");
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            logger = (Logger) field.get(clazz);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            logger = LoggerFactory.getLogger(clazz);
            logger.warn("目标类中无默认logger，建议添加。");
        }

        // 获取方法名
        String methodName = clazz.getName().concat(joinPoint.getSignature().getName());

        // 记录函数参数
        logger.info("方法: {}, 参数: {}", methodName, Arrays.toString(joinPoint.getArgs()));

        // 函数开始时间
        long start = System.currentTimeMillis();

        // 执行函数
        Object obj;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            logger.error("方法: {}, 发生异常: {}", methodName, e.getMessage());
            throw e;
        }

        // 函数结束时间
        long end = System.currentTimeMillis();

        // 记录函数返回值
        logger.info("方法: {}, 返回值: {}", methodName, obj);

        // 记录函数耗时
        logger.info("方法: {}, 耗时: {} ms", methodName, (end - start));

        return obj;
    }
}
