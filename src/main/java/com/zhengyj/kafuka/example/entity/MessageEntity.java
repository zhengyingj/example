package com.zhengyj.kafuka.example.entity;

import com.alibaba.fastjson.JSON;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @Author zhengyingjun
 * @Description ：消息实体类（由于使用lombok故不需要get set 方法）
 * @Date 2019/4/10
 **/
@Getter
@Setter
/**此注解会生成equals(Object other) 和 hashCode()方法
 *
 */
@EqualsAndHashCode
public class MessageEntity {

    private String title;

    private String body;

    @Override
    public String toString(){
        return JSON.toJSONString(this);

    }

}
