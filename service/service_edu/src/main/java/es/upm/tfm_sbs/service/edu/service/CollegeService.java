package es.upm.tfm_sbs.service.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.service.edu.entity.College;
import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.edu.entity.query.CollegeQuery;

import java.util.Map;

public interface CollegeService extends IService<College> {

    IPage<College> selectPage(Page<College> pageParam, CollegeQuery collegeQuery);

    boolean removeAvatarById(String id);
    Map<String, Object> selectCollegeInfoById(String id);
}
