package es.upm.tfm_sbs.service.edu.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.ExceptionUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.edu.entity.query.SubjectQuery;
import es.upm.tfm_sbs.service.edu.service.SubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/edu/subject")
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation(value = "Excel批量导入课程类别数据")
    @PostMapping("import")
    public Result batchImport(
            @ApiParam(value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            subjectService.batchImport(inputStream);
            return Result.ok().message("批量导入成功");
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new SbsException(ResultCode.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    //嵌套数据列表
    @GetMapping("nested-list")
    public Result nestedList(){
        List<SubjectQuery> subjectVoList = subjectService.nestedList();
        return Result.ok().data("items", subjectVoList);
    }
}