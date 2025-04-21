package com.microservice.userservice.consumer;

import com.microservice.userservice.entities.UserInfoDto;
import com.microservice.userservice.repository.UserRepository;
import com.microservice.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")                               
    public void listen(UserInfoDto userInfoDto){
        try{
            userService.createOrUpdateUser(userInfoDto);
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("AuthServiceConsumer: Exception is thrown while consuming Kafka event!");
        }
    }
}
