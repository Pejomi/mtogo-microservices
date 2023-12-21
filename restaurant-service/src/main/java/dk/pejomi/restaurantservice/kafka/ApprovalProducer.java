package dk.pejomi.restaurantservice.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApprovalProducer {

    @Value("${spring.kafka.approval_topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ApprovalProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public String sendMessage(String message){
        kafkaTemplate.send(topicName, message);
        return message;
    }

}
