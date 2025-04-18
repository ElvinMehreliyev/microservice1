package com.example.order_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Service;

@Configuration
public class KafkaTopic {

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topicName)
                .build();
    }


}
