package es.upm.tfm_sbs.service.edu.feign.fallback;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.feign.OssFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OssFileServiceFallBack implements OssFileService {

    @Override
    public Result test() {
        return Result.error();
    }

    @Override
    public Result removeFile(String url) {
        log.info("Fuse protection!熔断保护");
        return Result.error();
    }
}
