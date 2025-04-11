package io.github.lucklike.luckyclient.api.restTepmlate;

import io.github.lucklike.entity.request.User;
import io.github.lucklike.entity.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RestTemplateDemo {

    private final RestTemplate restTemplate = new RestTemplate();


    public Result<String> fileUpload() throws IOException {
        // 获取URL
        String url = "http://localhost:8864/file/upload";
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        File file = new File("D:\\id\\card\\营业执照3.jpg");
        File file2 = new File("D:\\id\\card\\zgyh.jpg");

        // 创建请求体
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("id", UUID.randomUUID().toString());
        body.add("file", getFileResource(file));
        body.add("file", getFileResource(file2));

        // 创建 HttpEntity
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发起请求+解析响应体
        ParameterizedTypeReference<Result<String>> responseType = new ParameterizedTypeReference<Result<String>>() {
        };
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType).getBody();
    }


    public Resource getFileResource(File file) throws IOException {
        byte[] bytes = FileCopyUtils.copyToByteArray(file);
        return new InputStreamResource(new ByteArrayInputStream(bytes)) {
            //文件长度,单位字节
            @Override
            public long contentLength() throws IOException {
                return bytes.length;
            }

            //文件名
            @Override
            public String getFilename() {
                return file.getName();
            }
        };
    }

    public Result<User> postUser() {
        // 获取URL
        String url = "http://localhost:8864/user/post";
        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        User user = new User();
        user.setId(12);
        user.setName("aaa");
        user.setAge(18);
        user.setEmail("aaa@qq.com");
        user.setPassword("123456");
        user.setPhone("1231");

        HttpEntity<User> httpReq = new HttpEntity<>(user, headers);

        ParameterizedTypeReference<Result<User>> responseType = new ParameterizedTypeReference<Result<User>>() {
        };
        return restTemplate.exchange(url, HttpMethod.POST, httpReq, responseType).getBody();
    }

}
