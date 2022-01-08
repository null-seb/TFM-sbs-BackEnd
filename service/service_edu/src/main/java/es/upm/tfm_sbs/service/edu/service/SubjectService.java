package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.Subject;
import es.upm.tfm_sbs.service.edu.entity.query.SubjectQuery;

import java.io.InputStream;
import java.util.List;

public interface SubjectService extends IService<Subject> {

    void batchImport(InputStream inputStream);
    List<SubjectQuery> nestedList();
}
