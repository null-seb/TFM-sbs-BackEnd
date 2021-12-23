package es.upm.tfm_sbs.service.edu.controller;


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
    public List<College> listAll(){
        return collegeService.list();
    }
}

