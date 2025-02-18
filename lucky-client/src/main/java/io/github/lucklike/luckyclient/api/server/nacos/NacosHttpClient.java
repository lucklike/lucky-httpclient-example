package io.github.lucklike.luckyclient.api.server.nacos;

import com.luckyframework.httpclient.proxy.annotations.DomainNameMeta;
import com.luckyframework.httpclient.proxy.annotations.ObjectGenerate;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/18 23:07
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@HttpClientComponent
@DomainNameMeta(getter = @ObjectGenerate(NacosDomainGetter.class))
public @interface NacosHttpClient {

    /**
     * 协议（http or https）
     */
    String protocol() default "http";

    String name();

    String group();
}
