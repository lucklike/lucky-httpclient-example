package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.proxy.plugin.ProxyDecorator;
import com.luckyframework.reflect.Param;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {


    @Pointcut("@annotation(UseMyPlugin)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("Before Aspect");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("After Aspect");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String a1 = "Aspect--" + args[0];
        String a2 = "Aspect--" + args[1];
        return joinPoint.proceed(new Object[]{a1, a2});
    }
}
