package es.upm.tfm_sbs.service.oss.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="aliyun.oss")
public class OssProperties {
    private String endpoint;
    private String keyId;
    private String keySecret;
    private String bucketName;
}
