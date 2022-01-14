package es.upm.tfm_sbs.service.cms.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.cms.entity.Ad;
import es.upm.tfm_sbs.service.cms.service.AdService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/cms/ad")
public class ApiAdController {

    private final AdService adService;
    private final RedisTemplate redisTemplate;

    @Autowired
    public ApiAdController(AdService adService, RedisTemplate redisTemplate) {
        this.adService = adService;
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public Result listByAdTypeId(@ApiParam(value = "推荐位id", required = true) @PathVariable String adTypeId) {

        List<Ad> ads = adService.selectByAdTypeId(adTypeId);
        return Result.ok().data("items", ads);
    }

    @PostMapping("save-test")
    public Result saveAd(@RequestBody Ad ad){
        //redisTemplate.opsForValue().set("ad1", ad);
        redisTemplate.opsForValue().set("index::ad", ad);
        return Result.ok();
    }

    @GetMapping("get-test/{key}")
    public Result getAd(@PathVariable String key){
        Ad ad = (Ad)redisTemplate.opsForValue().get(key);
        return Result.ok().data("ad", ad);
    }

    @DeleteMapping("remove-test/{key}")
    public Result removeAd(@PathVariable String key){
        Boolean delete = redisTemplate.delete(key);
        System.out.println(delete);//是否删除成功
        Boolean hasKey = redisTemplate.hasKey(key);
        System.out.println(hasKey);//key是否存在
        return Result.ok();
    }
}
