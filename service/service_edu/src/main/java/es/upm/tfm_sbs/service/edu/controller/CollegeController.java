package es.upm.tfm_sbs.service.edu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.entity.query.CollegeQuery;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
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

    @PostMapping("save")
    public Result save(@ApiParam(required = true) @RequestBody College college){
        boolean result=collegeService.save(college);
        if(result){
            return Result.ok().message("Saved Successful");
        }else {
            return Result.error().message("Saved Failed");
        }
    }

    @PutMapping("update")
    public Result updateById(@ApiParam(required = true) @RequestBody College college){
        boolean result=collegeService.updateById(college);
        if(result){
            return Result.ok().message("Update Successful");
        }else {
            return Result.error().message("Update Failed");
        }
    }

    @GetMapping("get/{id}")
    public Result getById(@ApiParam(required = true) @PathVariable String id){
        College college = collegeService.getById(id);
        if(Objects.nonNull(college)){
            return Result.ok().data("item",college);
        }else {
            return Result.error().message("Data not exist");
        }
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(required = true) @PathVariable String id){
        boolean result = collegeService.removeById(id);
        if(result){
            return Result.ok().message("Deleted successful");
        }else{
            return Result.error().message("Data not exist");
        }
    }
}

