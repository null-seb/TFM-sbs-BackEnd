package es.upm.tfm_sbs.service.cms.feign.fallback;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.cms.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public Result removeFile(String url) {
        log.info("熔断保护");
        return Result.error().message("timeout");
    }
}
