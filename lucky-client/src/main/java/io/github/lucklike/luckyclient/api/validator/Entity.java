package io.github.lucklike.luckyclient.api.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2025/10/24 00:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    @NotNull
    private Integer id;

    @Pattern(regexp = "^(A|B|C)?$", message = "类型必须是 A、B 或 C 中的一个")
    private String type;

    @NotBlank
    private String name;

    @Email
    private String email;
}
