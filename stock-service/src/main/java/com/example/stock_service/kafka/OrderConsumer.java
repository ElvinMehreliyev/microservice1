package com.example.stock_service.kafka;


import com.example.stock_service.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class OrderConsumer {

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @KafkaListener(topics ="${spring.kafka.topic.name}", groupId ="${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received: %s", orderEvent.toString()));
    }
}
