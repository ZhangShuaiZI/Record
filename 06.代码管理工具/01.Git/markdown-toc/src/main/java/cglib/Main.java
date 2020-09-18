package cglib;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <p>Title: Main</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 16:48
 */
public class Main {

    public static void main(String[] args) {
        try {
            Class<?> personClass = Class.forName("cglib.Person");
            Method getName = personClass.getMethod("getName", Integer.class);
            Field name = personClass.getDeclaredField("name");
            Constructor<?> constructors = personClass.getConstructor(null);
            Object objUser = constructors.newInstance();
            name.setAccessible(true);
            name.set(objUser, "zhangsan");
            Object invoke = getName.invoke(objUser, 1000);
            System.out.println(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
