package io.github.lucklike.server.ai.crh.req;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;


@Data
@EqualsAndHashCode(callSuper = true)
public class FundSumaryPublishRequest extends CrhBaseRequest {

    private String agreement_type;
    private MultipartFile[] files;

}
