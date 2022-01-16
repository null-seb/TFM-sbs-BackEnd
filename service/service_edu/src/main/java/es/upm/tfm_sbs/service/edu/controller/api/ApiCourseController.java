package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.query.ChapterQuery;
import es.upm.tfm_sbs.service.edu.entity.query.WebCourseQuery;
import es.upm.tfm_sbs.service.edu.entity.query.WebCourseQueryVo;
import es.upm.tfm_sbs.service.edu.service.ChapterService;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {

    private final CourseService courseService;
    private final ChapterService chapterService;

    @Autowired
    public ApiCourseController(CourseService courseService, ChapterService chapterService) {
        this.courseService = courseService;
        this.chapterService = chapterService;
    }

    @GetMapping("list")
    public Result list(@ApiParam(required = true) WebCourseQueryVo webCourseQueryVo){
        List<Course> courseList = courseService.webSelectList(webCourseQueryVo);
        return  Result.ok().data("courseList", courseList);
    }

    @GetMapping("get/{courseId}")
    public Result getById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable String courseId){

        //查询课程信息和讲师信息
        WebCourseQuery webCourseVo = courseService.selectWebCourseVoById(courseId);

        //查询当前课程的章节信息
        List<ChapterQuery> chapterVoList = chapterService.nestedList(courseId);

        return Result.ok().data("course", webCourseVo).data("chapterVoList", chapterVoList);
    }
}
