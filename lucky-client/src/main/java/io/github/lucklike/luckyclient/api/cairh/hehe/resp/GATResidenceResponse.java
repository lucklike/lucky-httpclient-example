package io.github.lucklike.luckyclient.api.cairh.hehe.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class GATResidenceResponse extends HeheBaseResp {

    private Integer duration;
    private Data data;

    @lombok.Data
    public static class Data {
        private List<Item> single_data_list;
        private List<Item> object_data_list;
    }

    @lombok.Data
    public static class Item {
        private String key;
        private String value;
        private String position;
    }
}
