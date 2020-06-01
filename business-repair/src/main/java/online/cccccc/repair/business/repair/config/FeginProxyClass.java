package online.cccccc.repair.business.repair.config;

import online.cccccc.repair.business.repair.annotation.CqFeginService;
import online.cccccc.repair.business.repair.utils.HttpClientUtils;
import online.cccccc.repair.commons.dto.Result;
import online.cccccc.repair.commons.utils.MapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.web.bind.annotation.PostMapping;

import java.lang.reflect.Method;

/**
 * @author 你是电脑
 * @date 2019/11/12 - 15:37
 */
public class FeginProxyClass<T> implements MethodInterceptor {

    private String host="";
    private Class<T> feginInterface;
    FeginProxyClass(){}
    FeginProxyClass(Class<T> feginInterface){
        this.feginInterface=feginInterface;
    }

    private final HttpClientUtils httpClientUtils = HttpClientUtils.getInstance();

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        System.out.println("cglib");
        System.out.println("方法名称===" + method.getName());
        CqFeginService annotation = feginInterface.getAnnotation(CqFeginService.class);
        host = annotation.value();
        if (StringUtils.isBlank(host)) {
            host = annotation.host();
        }
        if (StringUtils.isBlank(host)) {
            throw new RuntimeException("host 不能为空");
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
