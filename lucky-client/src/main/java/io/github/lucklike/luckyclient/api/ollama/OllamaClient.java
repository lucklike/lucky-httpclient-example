package io.github.lucklike.luckyclient.api.ollama;

import io.github.lucklike.httpclient.annotation.HttpClient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@HttpClient("#{@ollamaConfigProperties.url}")
public @interface OllamaClient {
}
