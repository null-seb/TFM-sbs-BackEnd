package es.upm.tfm_sbs.service.statistics.controller.admin;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.statistics.service.DailyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/statistics/daily")
public class DailyController {

    private final DailyService dailyService;

    @Autowired
    public DailyController(DailyService dailyService) {
        this.dailyService = dailyService;
    }


    @ApiOperation("生成统计记录")
    @PostMapping("create/{day}")
    public Result createStatisticsByDay(
            @ApiParam("统计日期")
            @PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return Result.ok().message("Generate data successfully");
    }
}
