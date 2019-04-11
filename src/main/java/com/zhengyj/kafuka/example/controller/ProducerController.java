package com.zhengyj.kafuka.example.controller;

import com.zhengyj.kafuka.example.common.ErrorCode;
import com.zhengyj.kafuka.example.entity.MessageEntity;
import com.zhengyj.kafuka.example.common.Response;
import com.zhengyj.kafuka.example.producer.SimpleProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @Author zhengyingjun
 * @Description controller
 * @Date 2019/4/10
 **/

@Slf4j
@RestController
@RequestMapping("/kafka")
public class ProducerController {

    @Autowired
    private SimpleProducer simpleProducer;

    @Value("${kafka.topic.default}")
    private String topic;

    /**
     * 测试是否联通
     * @return
     */
    @RequestMapping(value = "/hello",method = RequestMethod.GET,produces = {"application/json"})
    public Response hello(){
        return new Response(ErrorCode.SUCCESS,"ok!");
    }

    @RequestMapping(value = "/send",method = RequestMethod.POST,produces = {"application/json"})
    public Response sendKafka(@RequestBody MessageEntity messageEntity){
        try{
            log.info("kafka消息：",messageEntity.toString());
            simpleProducer.send(topic,"key",messageEntity);
            log.info("发送成功！");
            return new Response(ErrorCode.SUCCESS,"恭喜，发送成功！");
        }catch(Exception e){
            log.error("发送失败",e);
            return new Response(ErrorCode.ERROR,"发送失败！");
        }
    }
}
