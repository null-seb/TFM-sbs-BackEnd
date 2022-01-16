package es.upm.tfm_sbs.service.vod.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.ExceptionUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.vod.service.VideoService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vod/media")
@Slf4j
public class ApiMediaController {

    private final VideoService videoService;

    public ApiMediaController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("get-play-auth/{videoSourceId}")
    public Result getPlayAuth(
            @ApiParam(required = true)
            @PathVariable String videoSourceId){

        try{
            String playAuth = videoService.getPlayAuth(videoSourceId);
            return  Result.ok().message("Get playAuth successfully").data("playAuth", playAuth);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SbsException(ResultCode.FETCH_PLAYAUTH_ERROR);
        }
    }
}
