package es.upm.tfm_sbs.service.oss.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.oss.service.FileService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@CrossOrigin
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
            @RequestParam("module") String module) throws IOException {

        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String uploadUrl = fileService.upload(inputStream, module, originalFilename);

        //返回result对象
        return Result.ok().message("File uploaded successfully!").data("url", uploadUrl);
    }
}
