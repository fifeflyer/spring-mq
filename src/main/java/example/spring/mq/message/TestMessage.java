package example.spring.mq.message;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TestMessage {

    @Value("#{T(example.spring.mq.helper.ResourceHelper).read('classpath:message/credit-card.json')}")
    private String creditCardMessage;

    @Value("#{T(example.spring.mq.helper.ResourceHelper).read('classpath:message/direct-debit.json')}")
    private String directDebitMessage;

    @Value("#{T(example.spring.mq.helper.ResourceHelper).read('classpath:message/invoice.json')}")
    private String invoiceMessage;

    @Value("#{T(example.spring.mq.helper.ResourceHelper).read('classpath:message/unknown.json')}")
    private String unknownMessage;
}
