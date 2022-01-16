package es.upm.tfm_sbs.service.statistics.feign;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.statistics.feign.fallback.UcenterMemberServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-ucenter", fallback = UcenterMemberServiceFallBack.class)
public interface UcenterMemberService {

    @GetMapping(value = "/admin/ucenter/member/count-register-num/{day}")
    Result countRegisterNum(@PathVariable("day") String day);
}
