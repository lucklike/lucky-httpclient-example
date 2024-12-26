package io.github.lucklike.luckyclient.doc;

import io.github.lucklike.entity.request.proto.PersonOuterClass;
import io.github.lucklike.luckyclient.api.mock.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LuckyApiTest {

    @Autowired
    private LuckyApi luckyApi;

    @Test
    void hello() {
        String hello = luckyApi.text("Jack", 18, "jack@gmail.com");
        System.out.println(hello);
    }

    @Test
    void file() {
        Map<String, Object> txtForm = new LinkedHashMap<>();
        txtForm.put("name", "Lucy");
        txtForm.put("sex", "女");
        txtForm.put("age", "18");
        File file = new File("/Users/fukang/Pictures/1234.jpg");
        luckyApi.file(txtForm, file);
    }

    @Test
    void fileArray(){
        Map<String, Object> txtForm = new LinkedHashMap<>();
        txtForm.put("name", "Tom");
        txtForm.put("sex", "男");
        txtForm.put("age", "28");

        // 构建文件参数
        File[] files = {
            new File("/Users/fukang/Pictures/1234.jpg"),
            new File("/Users/fukang/Pictures/avatar.jpg"),
            new File("/Users/fukang/Pictures/R-C.jpeg"),
        };

        luckyApi.fileArray(txtForm, files);
    }

    @Test
    void fileList(){
        Map<String, Object> txtForm = new LinkedHashMap<>();
        txtForm.put("name", "Tom");
        txtForm.put("sex", "男");
        txtForm.put("age", "28");

        // 构建文件参数
        List<File> fileList = Arrays.asList(
                new File("/Users/fukang/Pictures/1234.jpg"),
                new File("/Users/fukang/Pictures/avatar.jpg"),
                new File("/Users/fukang/Pictures/R-C.jpeg")
        );

        luckyApi.fileList(txtForm, fileList);
    }

    @Test
    void strResource() {
        luckyApi.strResource("file:/Users/fukang/Pictures/*.jpg");
    }

    @Test
    void byteFile() {
        String string = "Lucky Hello World!";
        luckyApi.byteFile(string.getBytes());
    }

    @Test
    void byteFil2e() {
        String string = "Lucky Hello World!";
        luckyApi.byteFile(string.getBytes(), "lucky.txt");
    }

    @Test
    void jsonTest() {
        Map<String, Object> jsonMap = new LinkedHashMap<>();
        jsonMap.put("name", "Tom");
        jsonMap.put("age", "28");
        jsonMap.put("interest", Arrays.asList("篮球", "羽毛球", "写代码"));
        luckyApi.jsonTest(jsonMap);
    }

    @Test
    void jsonTest2() {
        User user = new User();
        user.setId("1234");
        user.setName("Tom");
        user.setPhone("17366542234");
        user.setEmail("tom@gmail.com");
        user.setUserAddress(new String[]{"地址1", "地址2"});

        luckyApi.jsonTest2(user);
    }

    @Test
    void protobufTest() {
        PersonOuterClass.Person responsePerson = PersonOuterClass.Person.newBuilder()
                .setName("Jack")
                .setId(12)
                .setEmail("jack@gmail.com")
                .build();
        PersonOuterClass.Person protobuf = luckyApi.protobufB(responsePerson.toByteArray());
        System.out.println(protobuf);
    }

    @Test
    void userInfo() {
        luckyApi.br("file:/Users/fukang/Pictures/avatar.jpg");
    }

    @Test
    void jsonParam() {
        luckyApi.jsonParam("Jack", 18);
    }

    @Test
    void staticJsonBody() {
        luckyApi.propertiesJsonArray();
    }

    @Test
    void byteFiles() throws IOException {
        byte[][] bytesArray= new byte[2][];
        bytesArray[0] = FileCopyUtils.copyToByteArray(new File("//Users/fukang/Pictures/avatar.jpg"));
        bytesArray[1] = FileCopyUtils.copyToByteArray(new File("/Users/fukang/Pictures/1234.jpg"));
        luckyApi.byteFiles(bytesArray);
    }

    @Test
    void byteMap() throws IOException {
        Map<String, Byte[]> byteMap = new LinkedHashMap<>();
        byteMap.put("text.txt", new Byte[]{'t', 'e', 's', 't'});
        byteMap.put("1234.jpg", toByteObjectArray(FileCopyUtils.copyToByteArray(new File("/Users/fukang/Pictures/1234.jpg"))) );
        luckyApi.byteMap(byteMap);
    }

    /**
     * 将 byte[] 转换为 Byte[]
     * @param byteArray 基本类型数组
     * @return 包装类型数组
     */
    public static Byte[] toByteObjectArray(byte[] byteArray) {
        // 创建一个与 byteArray 长度相同的 Byte 数组
        Byte[] byteObjectArray = new Byte[byteArray.length];

        // 将每个 byte 转换为 Byte 并存入 Byte[] 中
        for (int i = 0; i < byteArray.length; i++) {
            byteObjectArray[i] = Byte.valueOf(byteArray[i]);
        }

        return byteObjectArray;
    }
}