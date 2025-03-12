package io.github.lucklike.discovery.nacos.cairh;

import com.luckyframework.exception.LuckyRuntimeException;

public class BizException extends LuckyRuntimeException {
    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable ex) {
        super(ex);
    }

    public BizException(String message, Throwable ex) {
        super(message, ex);
    }

    public BizException(String messageTemplate, Object... args) {
        super(messageTemplate, args);
    }

    public BizException(Throwable ex, String messageTemplate, Object... args) {
        super(ex, messageTemplate, args);
    }
}
