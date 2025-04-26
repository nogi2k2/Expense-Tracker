package authservice.eventProducer;

import authservice.model.UserInfoDto;
import authservice.eventProducer.UserInfoEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Service
public class UserInfoProducer {
	
	private final KafkaTemplate<String, UserInfoDto> kafkaTemplate;

	@Value("${spring.kafka.topic-json.name}")
	private String topicJsonName;

	@Autowired
	UserInfoProducer(KafkaTemplate<String, UserInfoDto> kafkaTemplate){
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendToKafka(UserInfoEvent eventData){
		Message<UserInfoEvent> message = MessageBuilder.withPayload(eventData).setHeader(KafkaHeaders.TOPIC, "user_service").build();
		kafkaTemplate.send(message);
	}
}
