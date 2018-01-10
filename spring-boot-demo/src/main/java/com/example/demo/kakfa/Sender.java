package com.example.demo.kakfa;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 消息发送类
 * @author luohq
 * @Data   2017年12月11日
 * @Description TODO
 */
@Component
public class Sender {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMessage(){
        Message m = new Message();
        m.setId(System.currentTimeMillis());
        m.setMsg(UUID.randomUUID().toString());
        m.setSendTime(new Date());
        String jsonData = JSON.toJSONString(m, SerializerFeature.DisableCircularReferenceDetect);
        kafkaTemplate.send("test1", jsonData);
    }
}
