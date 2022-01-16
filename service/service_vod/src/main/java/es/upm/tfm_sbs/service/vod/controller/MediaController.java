package es.upm.tfm_sbs.service.vod.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.ExceptionUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/vod/media")
@Slf4j
public class MediaController {

    private final VideoService videoService;

    @Autowired
    public MediaController(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("upload")
    public Result uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String videoId = videoService.uploadVideo(inputStream, originalFilename);
            return Result.ok().message("Video upload successfully").data("videoId", videoId);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SbsException(ResultCode.VIDEO_UPLOAD_TOMCAT_ERROR);
        }

    }
    @DeleteMapping("remove/{vodId}")
    public Result removeVideo(
            @ApiParam(value="阿里云视频id", required = true)
            @PathVariable String vodId){

        try {
            videoService.removeVideo(vodId);
            return Result.ok().message("视频删除成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SbsException(ResultCode.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }
}
