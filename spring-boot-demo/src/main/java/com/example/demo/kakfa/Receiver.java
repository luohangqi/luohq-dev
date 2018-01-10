package com.example.demo.kakfa;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class Receiver {


    @KafkaListener(topics = "test1")
    public void processMessage(String content) {
        Message m = JSON.parseObject(content, Message.class);
    }
}
