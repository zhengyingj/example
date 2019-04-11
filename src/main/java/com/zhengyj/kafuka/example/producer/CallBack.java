package com.zhengyj.kafuka.example.producer;

import com.zhengyj.kafuka.example.entity.MessageEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFutureCallback;
/**
 *
 * @Author zhengyingjun
 * @Description ：返回信息
 * @Date 2019/4/10
 **/

@Slf4j
public class CallBack implements ListenableFutureCallback<SendResult<String, MessageEntity>> {
    /**
     * 开始发送时间
     */
    private final long startTime;

    private final String key;

    private final MessageEntity messageEntity;

    /**
     * 生成构造方法供调用
     * @param startTime
     * @param key
     * @param messageEntity
     */
    public CallBack(long startTime, String key, MessageEntity messageEntity) {
        this.startTime = startTime;
        this.key = key;
        this.messageEntity = messageEntity;
    }


    /**
     * 成功
     * @param stringMessageEntitySendResult
     */
    @Override
    public void onSuccess(@Nullable SendResult<String, MessageEntity> stringMessageEntitySendResult) {
        if(stringMessageEntitySendResult == null){
            return;
        }
        long costTime = System.currentTimeMillis() - startTime;
        //获取接受者
        RecordMetadata metadata = stringMessageEntitySendResult.getRecordMetadata();

        if(metadata != null){
           StringBuffer record = new StringBuffer();
           record.append("message(");
           record.append("key = ").append(key).append(",");
           record.append("message = ").append(messageEntity).append(")");
           record.append("发送给：Partition( ").append(metadata.partition()).append(")");
           record.append("消耗：").append(costTime).append(" ms");
           log.info(record.toString());
        }
    }
    /**
     * 失败并打印
     * @param throwable
     */
    @Override
    public void onFailure(Throwable throwable) {
        throwable.printStackTrace();
    }
}
