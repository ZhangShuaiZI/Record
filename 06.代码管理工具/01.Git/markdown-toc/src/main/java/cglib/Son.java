package cglib;

import com.sun.deploy.util.ArrayUtil;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

/**
 * <p>Title: Son</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/14 0014 16:08
 */
public class Son extends Person {

    public Son(String name) {
        super(name);
    }

    public static void main(String[] args) {
        int[] intArray = new int[10000];
        ArrayUtils.contains(intArray,1000);
    }
}
