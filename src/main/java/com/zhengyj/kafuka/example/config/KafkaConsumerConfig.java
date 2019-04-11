package com.zhengyj.kafuka.example.config;
import com.zhengyj.kafuka.example.entity.MessageEntity;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @Author zhengyingjun
 * @Description ：kafka 消费者端设置
 * @Date 2019/4/11
 **/
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${kafka.consumer.servers}")
    private String servers;

    @Value("${kafka.consumer.enable.auto.commit}")
    private boolean enableAutoCommit;

    @Value("${kafka.consumer.session.timeout}")
    private String sessionTimeout;

    @Value("${kafka.consumer.auto.commit.interval}")
    private String autoCommitInterval;

    @Value("${kafka.consumer.group.id}")
    private String groupid;

    @Value("${kafka.consumer.auto.offset.reset}")
    private String autoOffsetReset;

    @Value("${kafka.consumer.concurrency}")
    private int concurrency;

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MessageEntity>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,MessageEntity> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(concurrency);
        factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }

    private ConsumerFactory<String,MessageEntity> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                //key 反序列化
                new StringDeserializer(),
                //对象反序列化
                new JsonDeserializer<>(MessageEntity.class)
        );
    }

    private Map<String,Object> consumerConfigs(){
        Map<String,Object> map = new HashMap<>();
        map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
        map.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,enableAutoCommit);
        map.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,autoCommitInterval);
        map.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,sessionTimeout);
        map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        map.put(ConsumerConfig.GROUP_ID_CONFIG,groupid);
        map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,autoOffsetReset);
        return map;
    }

}
