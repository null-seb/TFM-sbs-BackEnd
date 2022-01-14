package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {

    private final CourseService courseService;

    private final CollegeService collegeService;

    @Autowired
    public ApiIndexController(CourseService courseService, CollegeService collegeService) {
        this.courseService = courseService;
        this.collegeService = collegeService;
    }

    @GetMapping
    public Result index(){

        //获取首页最热门前8条课程数据
        List<Course> courseList = courseService.selectHotCourse();
        //获取首页推荐前4条讲师数据
        List<College> teacherList = collegeService.selectHotCollege();

        return Result.ok().data("courseList", courseList).data("collegeList", teacherList);
    }
}
