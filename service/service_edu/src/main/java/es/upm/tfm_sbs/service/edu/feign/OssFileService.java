package es.upm.tfm_sbs.service.edu.feign;

import es.upm.tfm_sbs.common.base.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("service-oss")
public interface OssFileService {

    @GetMapping("/oss/file/test")
    Result test();

    @DeleteMapping("/oss/file/remove")
    Result removeFile(@RequestBody String url);
}
