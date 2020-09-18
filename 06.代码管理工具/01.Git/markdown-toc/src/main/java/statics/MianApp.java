package statics;

/**
 * <p>Title: MianApp</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 16:29
 */
public class MianApp {
    public static void main(String[] args) {
        Org org = new ProxyOrg("orgName");
        org.org();
    }
}
