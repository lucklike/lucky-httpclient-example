package io.github.lucklike.luckyclient.api.lucky;

import com.luckyframework.httpclient.proxy.annotations.DomainName;
import com.luckyframework.httpclient.proxy.annotations.SSL;
import com.luckyframework.httpclient.proxy.spel.SpELImport;
import io.github.lucklike.httpclient.annotation.HttpClientComponent;
import io.github.lucklike.httpclient.annotation.ProxyModel;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
@SSL
@DomainName("#{ann($cc$, 'io.github.lucklike.luckyclient.api.lucky.LAPI').url}")
@SpELImport(root = {"768976=#{_base64_str('572X6Imz546y')}"})
@HttpClientComponent
public @interface LAPI {

    /**
     * 配置Bean的名称，同{@link Component#value()}
     */
    @AliasFor(annotation = HttpClientComponent.class, attribute = "name")
    String name() default "";

    /**
     * URL配置
     */
    String url() default "https://api.luckyclient.com";

    /**
     * 代理模式
     */
    @AliasFor(annotation = HttpClientComponent.class, attribute = "proxyModel")
    ProxyModel proxyModel() default ProxyModel.DEFAULT;
}
