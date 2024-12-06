package io.github.lucklike.luckyclient.api.timeless;

import lombok.Data;

import java.util.List;

@Data
public class District {

    private String code;
    private String name;
    private List<District> children;

}
