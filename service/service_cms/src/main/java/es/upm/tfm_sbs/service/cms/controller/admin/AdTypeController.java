package es.upm.tfm_sbs.service.cms.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.cms.entity.AdType;
import es.upm.tfm_sbs.service.cms.service.AdTypeService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/cms/ad-type")
@Slf4j
public class AdTypeController {

    private final AdTypeService adTypeService;

    @Autowired
    public AdTypeController(AdTypeService adTypeService) {
        this.adTypeService = adTypeService;
    }

    @GetMapping("list")
    public Result listAll() {
        List<AdType> list = adTypeService.list();
        return Result.ok().data("items", list);
    }

    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(required = true) @PathVariable Long page,
                      @ApiParam(required = true) @PathVariable Long limit) {

        Page<AdType> pageParam = new Page<>(page, limit);
        IPage<AdType> pageModel = adTypeService.page(pageParam);
        List<AdType> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return Result.ok().data("total", total).data("rows", records);
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(required = true) @PathVariable String id) {

        boolean result = adTypeService.removeById(id);
        if (result) {
            return Result.ok().message("Delete successfully!");
        } else {
            return Result.error().message("Data not exist!");
        }
    }

    @PostMapping("save")
    public Result save(@ApiParam(required = true) @RequestBody AdType adType) {

        boolean result = adTypeService.save(adType);
        if (result) {
            return Result.ok().message("Save successfully!");
        } else {
            return Result.error().message("Save failed!");
        }
    }

    @PutMapping("update")
    public Result updateById(@ApiParam(required = true) @RequestBody AdType adType) {
        boolean result = adTypeService.updateById(adType);
        if (result) {
            return Result.ok().message("Update successfully!");
        } else {
            return Result.error().message("Data not exist!");
        }
    }

    @GetMapping("get/{id}")
    public Result getById(@ApiParam(required = true) @PathVariable String id) {
        AdType adType = adTypeService.getById(id);
        if (adType != null) {
            return Result.ok().data("item", adType);
        } else {
            return Result.error().message("Data not exist!");
        }
    }
}
