package es.upm.tfm_sbs.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import es.upm.tfm_sbs.service.edu.entity.CourseCollect;
import es.upm.tfm_sbs.service.edu.entity.query.CourseCollectVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseCollectMapper extends BaseMapper<CourseCollect> {
    List<CourseCollectVo> selectPageByMemberId(String memberId);
}
