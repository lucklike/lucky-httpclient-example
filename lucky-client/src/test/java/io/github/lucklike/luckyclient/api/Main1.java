package io.github.lucklike.luckyclient.api;

import com.luckyframework.conversion.ConversionUtils;
import com.luckyframework.io.RepeatableReadByteInputStream;
import com.luckyframework.io.RepeatableReadFileInputStream;
import com.luckyframework.reflect.ASMUtil;
import com.luckyframework.reflect.ClassUtils;
import io.github.lucklike.luckyclient.api.spark.SparkCompletionsEventListener;
import io.github.lucklike.luckyclient.doc.LuckyApi;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static com.luckyframework.httpclient.proxy.CommonFunctions.hex;
import static com.luckyframework.httpclient.proxy.CommonFunctions.md5;
import static com.luckyframework.httpclient.proxy.CommonFunctions.md5Hex;
import static com.luckyframework.httpclient.proxy.CommonFunctions.resourceAsStream;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha224;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha256;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha384;
import static com.luckyframework.httpclient.proxy.CommonFunctions.sha512;

public class Main1 {


    public static void main(String[] args) throws Exception {
//        streamTest();
        Long conversion = ConversionUtils.conversion("204955342356876891", Long.class);
        System.out.println(conversion);
    }

    private static void methodTest() {
        Method[] declaredMethodOrder = ASMUtil.getAllMethodOrder(LuckyApi.class);
        Stream.of(declaredMethodOrder).forEach(m -> System.out.println(m));

        System.out.println("-------------------------------------");

        Method[] declaredMethodOrder2 = ClassUtils.getAllMethod(LuckyApi.class);
        Stream.of(declaredMethodOrder2).forEach(m -> System.out.println(m));
    }

    private static void fieldTest() {
        Field[] declaredMethodOrder = ASMUtil.getAllFieldsOrder(SparkCompletionsEventListener.class);
        Stream.of(declaredMethodOrder).forEach(m -> System.out.println(m.getName()));

        System.out.println("-------------------------------------");

        Field[] declaredMethodOrder2 = ClassUtils.getAllFields(SparkCompletionsEventListener.class);
        Stream.of(declaredMethodOrder2).forEach(m -> System.out.println(m.getName()));
    }

    private static void md5Test() throws Exception {
        String data = "MD5 Hello World!";
        String s1 = md5Hex(data);
        String s2 = hex(md5(data));

        String s3 = hex(sha224(data));

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.println(hex(sha256(data)));
        System.out.println(hex(sha384(data)));
        System.out.println(hex(sha512(data)));

    }

    private static void streamTest() throws IOException {
        InputStream stream = resourceAsStream("file:D:\\Lucky\\lucky-httpclient-example\\lucky-client\\src\\main\\resources\\books.json");
        RepeatableReadFileInputStream rin = new RepeatableReadFileInputStream(stream);
        System.out.println(new String(FileCopyUtils.copyToByteArray(rin)));
        System.out.println(new String(FileCopyUtils.copyToByteArray(rin)));
        System.out.println(new String(FileCopyUtils.copyToByteArray(rin)));
        rin.deleteStorageMedium();
    }
}
