package example.spring.mq.message;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MessagingPropertyManager {

    @Value("${mq.hostname}")
    private String hostname;

    @Value("${mq.port}")
    private int port;

    @Value("${mq.queue}")
    private String queue;

    @Value("${mq.queue.manager}")
    private String queueManager;

    @Value("${mq.application.name}")
    private String applicationName;

    @Value("${mq.channel}")
    private String channel;

    @Value("${mq.user}")
    private String user;

    @Value("${mq.password}")
    private String password;

    @Value("${mq.user.authentication}")
    private boolean userAuthenticationEnabled;
}
