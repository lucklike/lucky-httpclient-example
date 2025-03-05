package io.github.lucklike.entity.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:39
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String password;
    private String email;
    private String phone;
}
