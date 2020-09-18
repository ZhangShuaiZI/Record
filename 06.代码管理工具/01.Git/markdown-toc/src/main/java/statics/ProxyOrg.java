package statics;

/**
 * <p>Title: ProxyOrg</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/13 0013 16:27
 */
public class ProxyOrg implements Org {

    private String orgName;

    private OrgImpl org;

    public ProxyOrg(String orgName){
        this.orgName = orgName;
    }

    public void org() {
        if (org == null ) {
            org = new OrgImpl(orgName);
        }
        org.org();
    }
}
