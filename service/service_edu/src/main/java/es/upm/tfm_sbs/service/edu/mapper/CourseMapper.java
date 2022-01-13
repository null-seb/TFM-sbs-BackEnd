package es.upm.tfm_sbs.service.edu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.query.CoursePublishQuery;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQuery;
import es.upm.tfm_sbs.service.edu.entity.query.WebCourseQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    List<CourseQuery> selectPageByCourseQueryVo(
            //mp会自动组装分页参数
            Page<CourseQuery> pageParam,
            //mp会自动组装queryWrapper：
            //@Param(Constants.WRAPPER) 和 xml文件中的 ${ew.customSqlSegment} 对应
            @Param(Constants.WRAPPER) QueryWrapper<CourseQuery> queryWrapper);

    CoursePublishQuery selectCoursePublishVoById(String id);
    WebCourseQuery selectWebCourseVoById(String courseId);
}
