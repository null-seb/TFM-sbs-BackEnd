package es.upm.tfm_sbs.service.edu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.CourseInfoForm;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQuery;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQueryVo;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return Result.ok().data("courseId", courseId).message("Save successfully");
    }

    @GetMapping("course-info/{id}")
    public Result getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String id){

        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        if (courseInfoForm != null) {
            return Result.ok().data("item", courseInfoForm);
        } else {
            return Result.error().message("Data not exist!");
        }
    }

    @PutMapping("update-course-info")
    public Result updateCourseInfoById(
            @ApiParam(value = "课程基本信息", required = true)
            @RequestBody CourseInfoForm courseInfoForm){

        courseService.updateCourseInfoById(courseInfoForm);
        return Result.ok().message("Modify successfully");
    }

    @GetMapping("list/{page}/{limit}")
    public Result index(
            @ApiParam(required = true)
            @PathVariable Long page,

            @ApiParam(required = true)
            @PathVariable Long limit,
                    CourseQueryVo courseQueryVo){

        IPage<CourseQuery> pageModel = courseService.selectPage(page, limit, courseQueryVo);
        List<CourseQuery> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return  Result.ok().data("total", total).data("rows", records);
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(required = true)
            @PathVariable String id){

        //在此处调用vod中的删除视频文件的接口

        //删除封面：OSS
        courseService.removeCoverById(id);
        //删除课程
        boolean result = courseService.removeCourseById(id);
        if (result) {
            return Result.ok().message("Deleted successfully");
        } else {
            return Result.error().message("Data not exist");
        }
    }
}
