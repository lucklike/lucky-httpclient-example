package io.github.lucklike.entity.request;

import lombok.Data;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/7/9 01:39
 */
@Data
public class User {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private String phone;
}
