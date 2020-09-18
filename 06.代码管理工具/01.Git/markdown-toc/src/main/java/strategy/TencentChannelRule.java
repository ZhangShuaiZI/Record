package strategy;

/**
 * <p>Title: TencentChannelRule</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/19 0019 12:12
 */
public class TencentChannelRule extends GeneralChannelRule {

    @Override
    public void process() {
        // Tencent处理逻辑
        System.out.println("TencentChannelRule.process");
    }
}
