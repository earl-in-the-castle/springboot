package com.example.firstspringboot.exception;

import com.example.firstspringboot.enums.ResultEnum;

/**
 * 自定义运行时异常
 * Created by 廖师兄
 * 2017-01-21 14:05
 */
public class GirlException extends RuntimeException{

    private Integer code;

    private  String girlExpDes;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
