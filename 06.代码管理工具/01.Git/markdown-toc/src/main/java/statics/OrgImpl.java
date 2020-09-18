package statics;

/**
 * <p>Title: OrgImpl</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 16:27
 */
public class OrgImpl implements Org {

    private String orgName;

    public OrgImpl(String orgName){
        this.orgName = orgName;
    }

    public void org() {
        System.out.println("OrgImpl.org" + orgName);
    }

}
