package io.github.lucklike.server.controller;

import io.github.lucklike.entity.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理器
 *
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/7 03:10
 */
@SuppressWarnings("all")
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandle {

    @ExceptionHandler(Exception.class)
    public Result exceptionHandle(Exception e) {
        log.error(e.getMessage(), e);
        return Result.fail(500, e.getMessage());
    }
}
