package com.tweetapp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Consumer {
	
	@KafkaListener(topics = "kafka_topic",groupId = "kafka_group")
	public void listenToTopic(String receivedMessage) {
		log.info("The message received is "+receivedMessage);
	}

}
