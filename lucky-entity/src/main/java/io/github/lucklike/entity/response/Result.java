package io.github.lucklike.entity.response;

import lombok.Data;

/**
 * 统一响应包装类
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:02
 */
@Data
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("Successful");
        result.setData(data);
        return result;
    }

    @SuppressWarnings("all")
    public static  Result fail(Integer code, String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
