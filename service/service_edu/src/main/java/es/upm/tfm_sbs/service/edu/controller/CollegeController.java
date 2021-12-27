package es.upm.tfm_sbs.service.edu.controller;


import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import org.springframework.web.bind.annotation.GetMapping;
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
}

