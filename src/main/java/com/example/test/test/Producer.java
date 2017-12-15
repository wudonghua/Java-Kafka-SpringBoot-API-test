package com.example.test.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author dw07-Riven770[wudonghua@gznb.com]
 * @Date 2017/12/1417:37
 */
@Component
@EnableScheduling
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Scheduled(fixedDelay = 6000)
    private void sendMsg(){
        kafkaTemplate.send("topic-test","key","Hello"+ LocalDateTime.now().toString());

    }
}
