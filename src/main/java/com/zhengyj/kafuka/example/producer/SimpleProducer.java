package com.zhengyj.kafuka.example.producer;

import com.zhengyj.kafuka.example.entity.MessageEntity;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 *
 * @Author zhengyingjun
 * @Description ：生产者
 * @Date 2019/4/10
 **/
@Component
public class SimpleProducer {

    @Autowired
    @Qualifier("kafkaTemplate")
    private KafkaTemplate<String, MessageEntity> kafkaTemplate;

    public void send(String topic, MessageEntity message){
        kafkaTemplate.send(topic,message);
    }

    public void send(String topic,String key, MessageEntity entity){
        ProducerRecord<String,MessageEntity> record = new ProducerRecord<>(topic,key,entity);

        long startTime = System.currentTimeMillis();
        //可监听处理
        ListenableFuture<SendResult<String,MessageEntity>> future = kafkaTemplate.send(record);
        //callback处理
        future.addCallback(new CallBack(startTime,key,entity));

    }

}
