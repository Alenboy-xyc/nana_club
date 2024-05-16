package com.shanzhu.subject.common.entity;

import com.shanzhu.subject.common.enums.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author shanzhu
 * @description 响应公共类
 * @create 2024/4/29
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    Result(){}

    public Result(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
    }

    public Result(T data, ResultCodeEnum resultCodeEnum) {
        this.data = data;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getDesc();
    }

    public static Result ok() {
        return new Result(null, ResultCodeEnum.SUCCESS);
    }

    public static Result ok(Object data) {
        return new Result(data, ResultCodeEnum.SUCCESS);
    }

    public static Result error() {
        return new Result(null, ResultCodeEnum.FAIL);
    }

    public static Result error(String message) {
        return secByError(ResultCodeEnum.FAIL.getCode(), message);
    }

    //自定义异常信息
    public static Result secByError(Integer code, String message) {
        Result err = new Result();
        err.setCode(code);
        err.setMessage(message);
        return err;
    }

    public static Result error(ResultCodeEnum respCode) {
        return new Result(respCode);
    }
}
