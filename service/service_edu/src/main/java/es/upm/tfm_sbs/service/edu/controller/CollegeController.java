package es.upm.tfm_sbs.service.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.entity.query.CollegeQuery;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/edu/college")
public class CollegeController {
    private CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("list")
    public Result listAll(){
        List<College> list=collegeService.list();
        return Result.ok().data("items",list).message("Get the list of colleges successfully.");
    }

    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(required = true) @PathVariable Long page,
                           @ApiParam(required = true) @PathVariable Long limit,
                           CollegeQuery collegeQuery){
        Page<College> pageParam=new Page<>(page,limit);
        IPage<College> pageModel=collegeService.selectPage(pageParam,collegeQuery);
        List<College> records = pageModel.getRecords();
        long total=pageModel.getTotal();
        return Result.ok().data("total",total).data("rows",records);
    }
}

