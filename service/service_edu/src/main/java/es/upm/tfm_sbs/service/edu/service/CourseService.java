package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.CourseInfoForm;
import es.upm.tfm_sbs.service.edu.entity.query.CoursePublishQuery;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQuery;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQueryVo;

public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourseInfoForm courseInfoForm);
    CourseInfoForm getCourseInfoById(String id);
    void updateCourseInfoById(CourseInfoForm courseInfoForm);

    IPage<CourseQuery> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    boolean removeCoverById(String id);

    boolean removeCourseById(String id);

    CoursePublishQuery getCoursePublishVoById(String id);

    boolean publishCourseById(String id);
}