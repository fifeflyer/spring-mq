package example.spring.mq.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;

@Component
public class MessageData {

    private final ObjectMapper objectMapper;

    public MessageData(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String eventType(String message) {
        try {
            return objectMapper.readTree(message).at("/eventType").asText("UNKNOWN");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
