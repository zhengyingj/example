package com.zhengyj.kafuka.example.consumer;
import com.zhengyj.kafuka.example.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 *
 * @Author zhengyingjun
 * @Description ：消费者
 * @Date 2019/4/11
 **/
@Slf4j
@Component
public class SimpleConsumer {
    /**指定topic
     *
     * @param messageEntity
     */
    @KafkaListener(topics = "${kafka.topic.default}",containerFactory = "kafkaListenerContainerFactory")
    public void receive(MessageEntity messageEntity){
        log.info(messageEntity.toString());
    }

}
