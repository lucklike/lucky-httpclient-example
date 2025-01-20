package io.github.lucklike.luckyclient.api;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.ZipUtil;
import com.luckyframework.io.RepeatableReadFileInputStream;
import com.luckyframework.reflect.ASMUtil;
import com.luckyframework.reflect.ClassUtils;
import io.github.lucklike.luckyclient.api.spark.SparkCompletionsEventListener;
import io.github.lucklike.luckyclient.doc.LuckyApi;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
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
        methodTest();
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

    private static void gzipTest() {
        String data = " {\n" +
                "    \"operator_image\": \"\",\n" +
                "    \"verify_result\": 1,\n" +
                "    \"user_role\": \",0,206002,1620,1,111111,04191,111222,999,008,102,0419,333333,444444,9527,04,06,0666,964,980,321,103,1023,553,911,11,01,111,10,201001,222222,944,945,946,996,112233,11333,062,992,50501,100,05,22,2,\",\n" +
                "    \"branch_no\": \"011212\",\n" +
                "    \"gender\": \"0\",\n" +
                "    \"user_id\": \"1163\",\n" +
                "    \"user_name\": \"付康\",\n" +
                "    \"expire_enddate\": 0,\n" +
                "    \"en_branch_nos\": \"0222,032312,4408,12345,3100,011212,100100,0107,210,6300,3333,711,6767,820,9999,9110,10111,377,8888,207,453434,213,100,9999991,811,856,10101,10102,911,3219,21301,333,4323,9527,1111111111111,4322,877,3,3344,7788,4324,4325,1211,2233,1102,1001,1023,1,103,101,10,999,2,99911,99922,99933,99912,66,1103,1101,68,0111,69,70,71,72,73,76,83,77,78,79,80,1008,86,87,89,99,42,105,106,107,109,22,23,24,25,26,27,28,29,30,34,37,39,40,41,43,45,108,199,11,12,16,17,75,18,20,118,122,725,7251,64,724,726,614051,21,31,32,33,35,38,50,51,52,53,55,56,57,58,59,60,61,62,63,65,67,82,110,113,115,117,112,111,120,119,121,116,6601,123,36,54,85,124,011,8035,4406,95521,1124,858,999999,123000,991,10010010,115501,115501001,115501002,115502,0314,03141,5500,9900,314,1155,20240506001,8887770,8887779,8887778\",\n" +
                "    \"en_roles\": \",0,206002,1620,1,111111,04191,111222,999,008,102,0419,333333,444444,9527,04,06,0666,964,980,321,103,1023,553,911,11,01,111,10,201001,222222,944,945,946,996,112233,11333,062,992,50501,100,05,22,2,\",\n" +
                "    \"staff_no\": \"1163\",\n" +
                "    \"status\": \"8\"\n" +
                "  }";
        String gStr = gzip(data, 1);
        System.out.println("压缩前：" + data);
        System.out.println("压缩后：" +gStr);

        System.out.println(data.length() + ", " + gStr.length() + ", 压缩比：" + gStr.length() / (data.length() * 1D));

    }

    private static String gzip(String data, int maxCount) {
        byte[] gBytes = data.getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < maxCount; i++) {
            gBytes = ZipUtil.gzip(gBytes);
        }
        return HexUtil.encodeHexStr(gBytes);
    }
}
