package com.zhengyj.kafuka.example.common;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @Author zhengyingjun
 * @Description ：对外暴露内容封装
 * @Date 2019/4/11
 **/

@Getter
@Setter
public class Response {

    private int code;

    private String message;

    public Response(int code , String message){
        this.code = code;
        this.message = message;
    }
}
