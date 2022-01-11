package es.upm.tfm_sbs.service.edu.feign;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.feign.fallback.VodMediaServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "service-vod", fallback = VodMediaServiceFallBack.class)
public interface VodMediaService {

    @DeleteMapping("/vod/media/remove/{vodId}")
    Result removeVideo(@PathVariable("vodId") String vodId);
}
