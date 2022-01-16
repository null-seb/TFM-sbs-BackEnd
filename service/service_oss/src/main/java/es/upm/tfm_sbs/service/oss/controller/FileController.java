package es.upm.tfm_sbs.service.oss.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.ExceptionUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.oss.service.FileService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/oss/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload")
    public Result upload(
            @RequestParam("file") MultipartFile file,
            @ApiParam(required = true)
            @RequestParam("module") String module) {

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);
            //返回result对象
            return Result.ok().message("File uploaded successfully!").data("url", uploadUrl);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SbsException(ResultCode.FILE_UPLOAD_ERROR);
        }

    }

    @GetMapping("test")
    public Result test() {
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

    @DeleteMapping("remove")
    public Result removeFile(
            @ApiParam(value = "Path of the file to be deleted", required = true)
            @RequestBody String url) {

        fileService.removeFile(url);
        return Result.ok().message("File deleted successfully");
    }
}
