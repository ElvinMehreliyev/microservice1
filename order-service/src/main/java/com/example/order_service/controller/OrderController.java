package com.example.order_service.controller;


import com.example.order_service.dto.OrderEvent;
import com.example.order_service.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderProducer orderProducer;
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String placeOrder(@RequestBody OrderEvent orderEvent) {


        orderProducer.sendMessage(orderEvent);
        return "Order placed";
    }


}
