package es.upm.tfm_sbs.service.edu.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.Video;
import es.upm.tfm_sbs.service.edu.service.VideoService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/edu/video")
@Slf4j
public class VideoController {

    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("save")
    public Result save(
            @ApiParam(required = true)
            @RequestBody Video video){
        boolean result = videoService.save(video);
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

        Video video = videoService.getById(id);
        if (video != null) {
            return Result.ok().data("item", video);
        } else {
            return Result.error().message("Data not exist");
        }
    }

    @PutMapping("update")
    public Result updateById(
            @ApiParam(required = true)
            @RequestBody Video video){

        boolean result = videoService.updateById(video);
        if (result) {
            return Result.ok().message("Modified successfully!");
        } else {
            return Result.error().message("Data not exist");
        }
    }

    @DeleteMapping("remove/{id}")
    public Result removeById(
            @ApiParam( required = true)
            @PathVariable String id){

        //TODO 删除视频：VOD
        //在此处调用vod中的删除视频文件的接口

        boolean result = videoService.removeById(id);
        if (result) {
            return Result.ok().message("Delete successfully");
        } else {
            return Result.error().message("Data not exist");
        }
    }

}
