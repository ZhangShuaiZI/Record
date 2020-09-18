import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Title: ProxyFactory</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 15:52
 */
public class ProxyFactory {

    private Object target;

    public ProxyFactory(Object target){
        this.target=target;
    }

    public Object getProxyInstance(){
        Object newProxyInstance = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(target, args);
                        return invoke;
                    }
                });
        return newProxyInstance;
    }

}
