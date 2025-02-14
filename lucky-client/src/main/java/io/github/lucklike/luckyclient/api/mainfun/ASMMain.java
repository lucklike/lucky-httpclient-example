package io.github.lucklike.luckyclient.api.mainfun;

import com.luckyframework.reflect.ASMUtil;

import java.lang.reflect.Method;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.function.Function;

public class ASMMain {

    public static void main(String[] args) {

        Method[] allMethodOrder = ASMUtil.getAllMethodOrder(MyFunction.class);
        for (Method method : allMethodOrder) {
            System.out.println(method);
        }
    }

    static class MyFunction  {

        @Override
        public String toString() {
            return "Hello World";
        }

        public String hh() {
            return "Hello World";
        }
    }
}
