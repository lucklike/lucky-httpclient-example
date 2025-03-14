package io.github.lucklike.luckyclient.api.cairh.comp.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 基金文件发布结果
 */
@Data
public class FundSumaryPublishResult {

    private int total;
    private int success;
    private int fail;

    private List<Details> successDetails = new ArrayList<>();
    private List<Details> failDetails = new ArrayList<>();


    public static FundSumaryPublishResult create(int total) {
        FundSumaryPublishResult result = new FundSumaryPublishResult();
        result.setTotal(total);
        return result;
    }

    public void addSuccessDetails(Details details) {
        successDetails.add(details);
        success++;
    }

    public void addFailDetails(Details details) {
        failDetails.add(details);
        fail++;
    }

    @Data
    public static class Details {
        private String fileName;
        private String description;

        public static Details create(String fileName, String description) {
            Details details = new Details();
            details.setFileName(fileName);
            details.setDescription(description);
            return details;
        }

        public static Details failByFileNameIllegality(String fileName) {
            String desc = String.format("基金产品资料概要协议文件名不符合要求，记录错误并忽略：%s", fileName);
            return Details.create(fileName, desc);
        }

        public static Details failByFileNotFound(String fileName) {
            String desc = String.format("基金产品资料概要协议文件不存在，记录错误并忽略：%s", fileName);
            return Details.create(fileName, desc);
        }

        public static Details failByProcessed(String fileName) {
            String desc = String.format("数据库已处理过该文件，忽略：%s", fileName);
            return Details.create(fileName, desc);
        }

    }
}
