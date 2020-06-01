package online.cccccc.repair.business.repair.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;

/**
 * @author lacia
 * @date 2020/5/22 - 19:03
 */
public class CqFeginFactoryBean<T>  implements FactoryBean<T> {
    private Class<T> feginInterface;
    CqFeginFactoryBean(){}
    CqFeginFactoryBean(Class<T> feginInterface){
        this.feginInterface = feginInterface;
    }
    @Override
    public T getObject() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(feginInterface);
        enhancer.setCallback(feginProxyClass());
        return (T) enhancer.create();
    }

    public Class<T> getFeginInterface() {
        return feginInterface;
    }

    public void setFeginInterface(Class<T> feginInterface) {
        this.feginInterface = feginInterface;
    }

    public FeginProxyClass<T> feginProxyClass(){
        return new FeginProxyClass<T>(feginInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.feginInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
