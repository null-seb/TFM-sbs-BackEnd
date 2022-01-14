package es.upm.tfm_sbs.service.cms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.cms.entity.Ad;
import es.upm.tfm_sbs.service.cms.entity.vo.AdVo;
import es.upm.tfm_sbs.service.cms.service.AdService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin/cms/ad")
@Slf4j
public class AdController {

    private final AdService adService;

    @Autowired
    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("save")
    public Result save(@ApiParam(required = true) @RequestBody Ad ad) {

        boolean result = adService.save(ad);
        if (result) {
            return Result.ok().message("Save successfully");
        } else {
            return Result.error().message("Save failed");
        }
    }

    @PutMapping("update")
    public Result updateById(@ApiParam(required = true) @RequestBody Ad ad) {
        boolean result = adService.updateById(ad);
        if (result) {
            return Result.ok().message("Update successfully!");
        } else {
            return Result.error().message("Data not exist!");
        }
    }

    @GetMapping("get/{id}")
    public Result getById(@ApiParam(required = true) @PathVariable String id) {
        Ad ad = adService.getById(id);
        if (ad != null) {
            return Result.ok().data("item", ad);
        } else {
            return Result.error().message("Data not exist!");
        }
    }

    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam( required = true) @PathVariable Long page,
                      @ApiParam(required = true) @PathVariable Long limit) {

        IPage<AdVo> pageModel = adService.selectPage(page, limit);
        List<AdVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {

        //删除图片
        adService.removeAdImageById(id);

        //删除推荐
        boolean result = adService.removeById(id);
        if (result) {
            return Result.ok().message("Delete successfully!");
        } else {
            return Result.error().message("Data not exist!");
        }
    }
}
