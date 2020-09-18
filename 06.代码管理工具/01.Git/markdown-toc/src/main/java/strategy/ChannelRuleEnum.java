package strategy;

/**
 * <p>Title: ChannelRuleEnum</p>
 * <p>Description:
 *
 * </p>
 *
 * @author ZhangShuai
 * @date 2020/8/19 0019 12:16
 */
public enum ChannelRuleEnum {

    /**
     * 头条
     */
    TOUTIAO("TOUTIAO", new TouTiaoChannelRule()),

    /**
     * 腾讯
     */
    TENCENT("TENCENT", new TencentChannelRule()),
    ;

    public String name;
    public GeneralChannelRule channel;

    ChannelRuleEnum(String name, GeneralChannelRule channel) {
        this.name = name;
        this.channel = channel;
    }

    /**
     * 匹配
     * @return
     */
    public static ChannelRuleEnum match(String name){
        ChannelRuleEnum[] values = ChannelRuleEnum.values();
        for (ChannelRuleEnum value: values) {
            if (value.name == name) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public GeneralChannelRule getChannel() {
        return channel;
    }
}
