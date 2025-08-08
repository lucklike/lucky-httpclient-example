package io.github.lucklike.luckyclient.api.cairh.htsec.resp;

import lombok.Data;

import java.util.Map;

@Data
public class V2IdCardResult {

    private String crop_image;
    private String risk;
    private String face;
    private String face_image;
    private String[] quad;
    private RecognizeResult recognize_result;




    @Data
    public static class RecognizeResult{
        /**
         * 姓名
         */
        private Item name;

        /**
         * 性别
         */
        private Item gender;

        /**
         * 名族
         */
        private Item nationality;

        /**
         * 身份证号
         */
        private Item idno;

        /**
         * 地址
         */
        private Item address;

        /**
         * 出生日期
         */
        private Item birthdate;

        /**
         * 有效日期
         */
        private Item issued;

        /**
         * 签发机关
         */
        private Item valid;
    }

    @Data
    public static class Item {
        private Map<String, Object> position;
        private String chinese_key;
        private String score;
        private String words;
        private String quad;
    }
}
