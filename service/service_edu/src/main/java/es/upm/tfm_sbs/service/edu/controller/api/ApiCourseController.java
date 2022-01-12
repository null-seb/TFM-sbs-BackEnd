package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.query.WebCourseQueryVo;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    private final CourseService courseService;

    @Autowired
    public ApiCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("list")
    public Result list(@ApiParam(required = true) WebCourseQueryVo webCourseQueryVo){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return  Result.ok().data("courseList", courseList);
    }
}
