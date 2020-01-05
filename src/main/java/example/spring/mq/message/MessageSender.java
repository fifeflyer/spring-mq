package example.spring.mq.message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Slf4j
public class MessageSender {

    private final JmsTemplate jmsTemplate;
    private final MessageData messageData;
    private final TestMessage testMessage;

    public void sendTestMessages() {
        Stream.of(
            testMessage.getCreditCardMessage(),
            testMessage.getDirectDebitMessage(),
            testMessage.getInvoiceMessage())
            .peek(message -> log.info("Sending test message [{}]", messageData.eventType(message)))
            .forEach(this::sendMessage);
    }

    private void sendMessage(String message) {
        jmsTemplate.convertAndSend(message);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
