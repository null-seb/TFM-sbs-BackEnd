package es.upm.tfm_sbs.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.edu.entity.Course;
import es.upm.tfm_sbs.service.edu.entity.CourseDescription;
import es.upm.tfm_sbs.service.edu.entity.CourseInfoForm;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQuery;
import es.upm.tfm_sbs.service.edu.entity.query.CourseQueryVo;
import es.upm.tfm_sbs.service.edu.mapper.CourseDescriptionMapper;
import es.upm.tfm_sbs.service.edu.mapper.CourseMapper;
import es.upm.tfm_sbs.service.edu.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public CourseInfoForm getCourseInfoById(String id) {

        //从course表中取数据
        Course course = baseMapper.selectById(id);
        if(course == null){
            return null;
        }

        //从course_description表中取数据
        CourseDescription courseDescription = courseDescriptionMapper.selectById(id);

        //创建courseInfoForm对象
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(course, courseInfoForm);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCourseInfoById(CourseInfoForm courseInfoForm) {
        //保存课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfoForm, course);
        baseMapper.updateById(course);

        //保存课程详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfoForm.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionMapper.updateById(courseDescription);
    }

    public IPage<CourseQuery> selectPage(Long page, Long limit, CourseQueryVo courseQueryVo) {

        QueryWrapper<CourseQuery> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String title = courseQueryVo.getTitle();
        String collegeId = courseQueryVo.getCollegeId();
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("c.title", title);
        }

        if (!StringUtils.isEmpty(collegeId) ) {
            queryWrapper.eq("c.college_id", collegeId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("c.subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("c.subject_id", subjectId);
        }

        Page<CourseQuery> pageParam = new Page<>(page, limit);
        //放入分页参数和查询条件参数，mp会自动组装
        List<CourseQuery> records = baseMapper.selectPageByCourseQueryVo(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }
}
