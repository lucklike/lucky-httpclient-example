package io.github.lucklike.luckyclient.api.deepseek;

import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import com.luckyframework.httpclient.proxy.annotations.StaticHeader;
import com.luckyframework.httpclient.proxy.annotations.StaticJsonBody;
import com.luckyframework.httpclient.proxy.creator.Scope;
import com.luckyframework.httpclient.proxy.sse.EventListener;
import com.luckyframework.httpclient.proxy.sse.Sse;
import com.luckyframework.reflect.Combination;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/28 02:20
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Sse
@StaticJsonBody("")
@StaticHeader("Authorization: Bearer ${DeepSeek.apiKey}")
public @interface DeepSeekChat {

    @AliasFor(annotation = StaticJsonBody.class, attribute = "value")
    String param() default "``#{#read(#resource('classpath:deepseek-param.json'))}``";

    @AliasFor(annotation = Sse.class, attribute = "listenerClass")
    Class<? extends EventListener> listenerClass() default DeepSeekEventListener.class;
}
