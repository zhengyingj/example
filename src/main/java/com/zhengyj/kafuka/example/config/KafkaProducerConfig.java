package com.zhengyj.kafuka.example.config;

import com.zhengyj.kafuka.example.entity.MessageEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author zhengyingjun
 * @Description ：Kafka 生产者端设置
 * @Date 2019/4/11
 **/
@Configuration
@EnableKafka
public class KafkaProducerConfig {
    @Value("${kafka.producer.servers}")
    private String servers;

    @Value("${kafka.producer.retries}")
    private int retries;

    @Value("${kafka.producer.batch.size}")
    private int batchSize;

    @Value("${kafka.producer.linger}")
    private int linger;

    @Value("${kafka.producer.buffer.memory}")
    private int bufferMemory;


    @Bean
    public KafkaTemplate<String, MessageEntity> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    public ProducerFactory<String,MessageEntity> producerFactory(){
        return new DefaultKafkaProducerFactory<>(
                producerConfigs(),
                new StringSerializer(),
                new JsonSerializer<MessageEntity>());
    }

    public Map<String,Object> producerConfigs(){
        Map<String,Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        map.put(ProducerConfig.RETRIES_CONFIG,retries);
        map.put(ProducerConfig.BATCH_SIZE_CONFIG,batchSize);
        map.put(ProducerConfig.LINGER_MS_CONFIG,linger);
        map.put(ProducerConfig.BUFFER_MEMORY_CONFIG,bufferMemory);
        map.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        map.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return map;
    }

}
