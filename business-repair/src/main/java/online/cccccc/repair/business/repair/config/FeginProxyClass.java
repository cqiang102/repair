package online.cccccc.repair.business.repair.config;

import online.cccccc.repair.business.repair.utils.HttpClientUtils;
import online.cccccc.repair.commons.dto.Result;
import online.cccccc.repair.commons.utils.MapperUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author 你是电脑
 * @create 2019/11/12 - 15:37
 */
public class FeginProxyClass implements MethodInterceptor {

    @Value("${provider.host}")
    private String host;

    @Resource
    private HttpClientUtils httpClientUtils;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib");
        System.out.println("方法名称===" + method.getName());
        for (Annotation annotation : o.getClass().getAnnotations()) {
            System.out.println("类注解===" + annotation.toString());
        }
        //发送 POST请求
        if (method.isAnnotationPresent(PostMapping.class)) {
            String[] value = method.getAnnotation(PostMapping.class).value();
            if(value.length>0){
                System.out.println("uri==="+value[0]);
                return post(value[0],objects);
            }
        }
        return null;
    }

    private Result post(String uri,Object[] objects) {
        try {
            //由于参数是 json 的便直接发过去了
            CloseableHttpResponse post = httpClientUtils.post("http://"+host+"/"+uri, MapperUtils.obj2json(objects[0]));
            HttpEntity entity = post.getEntity();
            String string = EntityUtils.toString(entity);
            System.out.println("收到响应==="+string);
            return MapperUtils.json2pojo(string,Result.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
