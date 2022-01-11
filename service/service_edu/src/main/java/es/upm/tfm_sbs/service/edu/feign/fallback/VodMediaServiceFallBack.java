package es.upm.tfm_sbs.service.edu.feign.fallback;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.feign.VodMediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VodMediaServiceFallBack implements VodMediaService {
    @Override
    public Result removeVideo(String vodId) {
        log.info("熔断保护");
        return Result.error();
    }
}
