package io.github.lucklike.luckyclient.api.mock;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * @author fukang
 * @version 1.0.0
 * @date 2024/8/18 23:31
 */
@Data
@XmlRootElement
public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String[] userAddress;
}
