package es.upm.tfm_sbs.service.edu.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.Chapter;
import es.upm.tfm_sbs.service.edu.entity.query.ChapterQuery;
import es.upm.tfm_sbs.service.edu.service.ChapterService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edu/chapter")
@Slf4j
public class ChapterController {
    private final ChapterService chapterService;

    @Autowired
    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping("save")
    public Result save(
            @ApiParam(required = true)
            @RequestBody Chapter chapter){
        boolean result = chapterService.save(chapter);
        if (result) {
            return Result.ok().message("Save successfully");
        } else {
            return Result.error().message("Save failed");
        }
    }

    @GetMapping("get/{id}")
    public Result getById(
            @ApiParam(required = true)
            @PathVariable String id){

        Chapter chapter = chapterService.getById(id);
        if (chapter != null) {
            return Result.ok().data("item", chapter);
        } else {
            return Result.error().message("Data not exist");
        }
    }

    @PutMapping("update")
    public Result updateById(
            @ApiParam(required = true)
            @RequestBody Chapter chapter){

        boolean result = chapterService.updateById(chapter);
        if (result) {
            return Result.ok().message("Modified successfully!");
        } else {
            return Result.error().message("Data not exist");
        }
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam(value = "章节ID", required = true)
            @PathVariable String id){

        //TODO 删除视频：VOD
        //在此处调用vod中的删除视频文件的接口

        boolean result = chapterService.removeChapterById(id);
        if (result) {
            return Result.ok().message("Delete successfully");
        } else {
            return Result.error().message("Data not exist");
        }
    }

    @GetMapping("nested-list/{courseId}")
    public Result nestedListByCourseId(
            @ApiParam(required = true)
            @PathVariable String courseId){

        List<ChapterQuery> chapterVoList = chapterService.nestedList(courseId);
        return Result.ok().data("items", chapterVoList);
    }
}
