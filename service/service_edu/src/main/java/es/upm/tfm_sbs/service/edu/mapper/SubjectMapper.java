package es.upm.tfm_sbs.service.edu.mapper;

import es.upm.tfm_sbs.service.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import es.upm.tfm_sbs.service.edu.entity.query.SubjectQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    List<SubjectQuery> selectNestedListByParentId(String parentId);
}
