package example.spring.mq.config;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import example.spring.mq.message.MessagingPropertyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

import static com.ibm.msg.client.jms.JmsConstants.*;
import static com.ibm.msg.client.wmq.common.CommonConstants.*;

@Configuration
@ComponentScan("example.spring.mq")
public class MessagingConfiguration {

    private final MessagingPropertyManager messagingPropertyManager;

    @Autowired
    public MessagingConfiguration(MessagingPropertyManager messagingPropertyManager) {
        this.messagingPropertyManager = messagingPropertyManager;
    }

    @Bean
    public JmsFactoryFactory jmsFactoryFactory() throws JMSException {
        return JmsFactoryFactory.getInstance(WMQ_PROVIDER);
    }

    @Bean
    public JmsConnectionFactory jmsConnectionFactory() throws JMSException {
        JmsConnectionFactory factory = jmsFactoryFactory().createConnectionFactory();
        factory.setStringProperty(WMQ_HOST_NAME, messagingPropertyManager.getHostname());
        factory.setIntProperty(WMQ_PORT, messagingPropertyManager.getPort());
        factory.setStringProperty(WMQ_CHANNEL, messagingPropertyManager.getChannel());
        factory.setIntProperty(WMQ_CONNECTION_MODE, WMQ_CM_CLIENT);
        factory.setStringProperty(WMQ_QUEUE_MANAGER, messagingPropertyManager.getQueueManager());
        factory.setStringProperty(WMQ_APPLICATIONNAME, messagingPropertyManager.getApplicationName());
        factory.setBooleanProperty(USER_AUTHENTICATION_MQCSP, messagingPropertyManager.isUserAuthenticationEnabled());
        factory.setStringProperty(USERID, messagingPropertyManager.getUser());
        factory.setStringProperty(PASSWORD, messagingPropertyManager.getPassword());
        return factory;
    }

    @Bean
    public SingleConnectionFactory singleConnectionFactory() throws JMSException {
        SingleConnectionFactory factory = new SingleConnectionFactory();
        factory.setTargetConnectionFactory(jmsConnectionFactory());
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestinationName(messagingPropertyManager.getQueue());
        jmsTemplate.setConnectionFactory(singleConnectionFactory());
        return jmsTemplate;
    }
}
