package es.upm.tfm_sbs.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.CourseDescription;
import es.upm.tfm_sbs.service.edu.entity.CourseInfoForm;
import es.upm.tfm_sbs.service.edu.mapper.CourseDescriptionMapper;
import es.upm.tfm_sbs.service.edu.mapper.CourseMapper;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    //注意：为了避免idea在这个位置报告找不到依赖的错误，
    //我们可以在CourseDescriptionMapper接口上添加@Repository注解
    private final CourseDescriptionMapper courseDescriptionMapper;
    @Autowired
    public CourseServiceImpl(CourseDescriptionMapper courseDescriptionMapper) {
        this.courseDescriptionMapper = courseDescriptionMapper;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {

        //保存课程基本信息
        Course course = new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.insert(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.insert(courseDescription);

        return course.getId();
    }
}
