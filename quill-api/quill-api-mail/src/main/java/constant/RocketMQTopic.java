package constant;

/**
 * @description: rocketmq 的 topic 和 tag
 * @author: tuberose
 * @date: 2024/6/12 23:11
 */
public class RocketMQTopic {

    public static final String MAIL_TOPIC = "MAIL";

    public static final String REGISTER_TAG = "REGISTER";

    /**
     * 注册验证码
     */
    public static final String REGISTER_MAIL = MAIL_TOPIC + ":" + REGISTER_TAG;

}
