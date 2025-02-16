package io.github.lucklike.luckyclient.api.plugin;

import com.luckyframework.httpclient.generalapi.describe.TokenApi;
import com.luckyframework.reflect.ClassUtils;
import com.luckyframework.reflect.FieldUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/2/16 12:57
 */
@SpringBootTest
class FieldApiTest {

    @Resource
    private FieldApi fieldApi;

    @Test
    void test() {
        Field[] allFields = ClassUtils.getAllFields(FieldApi.class);
        for (Field field : allFields) {
            Class<?> type = field.getType();
            Object value = null;
            if (type == Integer.class) {
                value = 111;
            } else if (type == String.class) {
                value = "FieldTestString";
            } else if (type == Boolean.class) {
                value = false;
            } else if (type == boolean.class) {
                value = true;
            }
            FieldUtils.setValue(fieldApi, field, value);
        }

        System.out.println("-------------------------");

        for (Field field : allFields) {
            Object value = FieldUtils.getValue(fieldApi, field);
            System.out.println(value);
        }
    }
}