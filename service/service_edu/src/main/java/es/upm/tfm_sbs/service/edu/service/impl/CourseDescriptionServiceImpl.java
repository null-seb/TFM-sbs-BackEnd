package es.upm.tfm_sbs.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.edu.entity.CourseDescription;
import es.upm.tfm_sbs.service.edu.mapper.CourseDescriptionMapper;
import es.upm.tfm_sbs.service.edu.service.CourseDescriptionService;
import org.springframework.stereotype.Service;

@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}