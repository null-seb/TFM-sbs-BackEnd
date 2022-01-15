package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.CourseCollect;
import es.upm.tfm_sbs.service.edu.entity.query.CourseCollectVo;

import java.util.List;

public interface CourseCollectService extends IService<CourseCollect> {
    boolean isCollect(String courseId, String id);

    void saveCourseCollect(String courseId, String id);

    List<CourseCollectVo> selectListByMemberId(String id);

    boolean removeCourseCollect(String courseId, String id);
}
