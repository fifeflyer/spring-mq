package example.spring.mq;

import example.spring.mq.message.MessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("example.spring.mq");

        MessageSender messageSender = context.getBean(MessageSender.class);
        messageSender.sendTestMessages();
    }
}
