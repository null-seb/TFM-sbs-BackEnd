package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/edu/college")
public class ApiCollegeController {

    private final CollegeService collegeService;

    @Autowired
    public ApiCollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @GetMapping("list")
    public Result listAll(){
        List<College> list = collegeService.list(null);
        return Result.ok().data("items", list).message("Get college list successfully");
    }

    @GetMapping("get/{id}")
    public Result get(
            @ApiParam(required = true)
            @PathVariable String id) {
        Map<String, Object> map = collegeService.selectCollegeInfoById(id);
        return Result.ok().data(map);
    }

}
