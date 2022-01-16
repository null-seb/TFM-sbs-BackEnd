package es.upm.tfm_sbs.service.statistics.feign.fallback;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.statistics.feign.UcenterMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UcenterMemberServiceFallBack implements UcenterMemberService {
    @Override
    public Result countRegisterNum(String day) {
        //错误日志
        log.error("熔断器被执行");
        return Result.ok().data("registerNum", 0);
    }
}
