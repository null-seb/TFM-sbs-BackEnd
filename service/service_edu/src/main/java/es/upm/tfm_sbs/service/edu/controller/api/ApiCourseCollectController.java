package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.util.JwtInfo;
import es.upm.tfm_sbs.common.base.util.JwtUtils;
import es.upm.tfm_sbs.service.edu.entity.query.CourseCollectVo;
import es.upm.tfm_sbs.service.edu.service.CourseCollectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/edu/course-collect")
@Slf4j
public class ApiCourseCollectController {

    private final CourseCollectService courseCollectService;

    @Autowired
    public ApiCourseCollectController(CourseCollectService courseCollectService) {
        this.courseCollectService = courseCollectService;
    }


    @ApiOperation(value = "判断是否收藏")
    @GetMapping("auth/is-collect/{courseId}")
    public Result isCollect(
            @ApiParam(name = "courseId", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean isCollect = courseCollectService.isCollect(courseId, jwtInfo.getId());
        return Result.ok().data("isCollect", isCollect);
    }

    @PostMapping("auth/save/{courseId}")
    public Result save(
            @ApiParam(name = "courseId", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        if(jwtInfo==null){
            return Result.error().message("Please login first!");
        }
        courseCollectService.saveCourseCollect(courseId, jwtInfo.getId());
        return Result.ok();
    }

    @GetMapping("auth/list")
    public Result collectList(HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        List<CourseCollectVo> list = courseCollectService.selectListByMemberId(jwtInfo.getId());
        return Result.ok().data("items", list);
    }

    @DeleteMapping("auth/remove/{courseId}")
    public Result remove(
            @ApiParam(name = "courseId", required = true)
            @PathVariable String courseId,
            HttpServletRequest request) {

        JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
        boolean result = courseCollectService.removeCourseCollect(courseId, jwtInfo.getId());
        if (result) {
            return Result.ok().message("Canceled");
        } else {
            return Result.error().message("Failed to cancel");
        }
    }
}
