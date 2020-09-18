package strategy;

/**
 * <p>Title: Main</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/19 0019 12:05
 */
public class Main {

    public static void main(String[] args) {
        ChannelRuleEnum toutiao = ChannelRuleEnum.match("TOUTIAO");
        GeneralChannelRule channel = toutiao.getChannel();
        channel.process();
    }

}
