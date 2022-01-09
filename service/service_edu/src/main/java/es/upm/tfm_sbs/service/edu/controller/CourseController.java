package es.upm.tfm_sbs.service.edu.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.CourseInfoForm;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/edu/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("save-course-info")
    public Result saveCourseInfo(
            @ApiParam( required = true)
            @RequestBody CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return Result.ok().data("courseId", courseId).message("保存成功");
    }

    @GetMapping("course-info/{id}")
    public Result getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        if (courseInfoForm != null) {
            return Result.ok().data("item", courseInfoForm);
        } else {
            return Result.error().message("数据不存在");
        }
    }

    @PutMapping("update-course-info")
    public Result updateCourseInfoById(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        courseService.updateCourseInfoById(courseInfoForm);
        return Result.ok().message("修改成功");
    }
}
