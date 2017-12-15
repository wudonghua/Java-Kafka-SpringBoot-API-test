package com.example.test.test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import riven.kafka.api.listener.IKafkaListener;

import java.util.Optional;

/**
 * @Author dw07-Riven770[wudonghua@gznb.com]
 * @Date 2017/12/1417:37
 */
@Component
public class Consumer implements IKafkaListener {

    @Autowired
    private TestBean testBean;

    @Override
    public void listener(ConsumerRecord<?, ?> record) {
        Optional<?> value = Optional.ofNullable(record.value());
        String s = (String) value.get();
        System.out.println("消费者"+s);
        testBean.test();
    }
}
