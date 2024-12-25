package io.github.lucklike.server.controller;

import io.github.lucklike.entity.request.proto.PersonOuterClass;
import org.springframework.web.bind.annotation.*;

import com.google.protobuf.InvalidProtocolBufferException;

@RestController
@RequestMapping("/protobuf")
public class ProtobufPersonController {

    // 接收 Protobuf 格式的请求
    @PostMapping(value = "/person", consumes = "application/x-protobuf", produces = "application/x-protobuf")
    public byte[] createPerson(@RequestBody byte[] personData) throws InvalidProtocolBufferException {
        // 反序列化 Protobuf 请求数据
        PersonOuterClass.Person person = PersonOuterClass.Person.parseFrom(personData);


        // 处理 Person 数据（例如打印或存储）
        System.out.println("Received Person: " + person.getName());

        // 创建响应的 Person 对象
        PersonOuterClass.Person responsePerson = PersonOuterClass.Person.newBuilder()
                .setName(person.getName())
                .setId(person.getId())
                .setEmail(person.getEmail())
                .build();

        // 将响应对象序列化为字节数组并返回
        return responsePerson.toByteArray();
    }
}
