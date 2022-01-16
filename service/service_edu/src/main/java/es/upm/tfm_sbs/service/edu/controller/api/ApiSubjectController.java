package es.upm.tfm_sbs.service.edu.controller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.edu.entity.query.SubjectQuery;
import es.upm.tfm_sbs.service.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    private final SubjectService subjectService;

    @Autowired
    public ApiSubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("nested-list")
    public Result nestedList(){
        List<SubjectQuery> subjectVoList = subjectService.nestedList();
        return Result.ok().data("items", subjectVoList);
    }
}
