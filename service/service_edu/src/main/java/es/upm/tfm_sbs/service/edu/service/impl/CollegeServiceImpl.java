package es.upm.tfm_sbs.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import es.upm.tfm_sbs.service.edu.entity.College;
import es.upm.tfm_sbs.service.edu.entity.query.CollegeQuery;
import es.upm.tfm_sbs.service.edu.mapper.CollegeMapper;
import es.upm.tfm_sbs.service.edu.service.CollegeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 学校 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2021-12-23
 */
@Service
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Override
    public IPage<College> selectPage(Page<College> pageParam, CollegeQuery collegeQuery) {
        QueryWrapper<College> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");

        if(collegeQuery == null){
            return baseMapper.selectPage(pageParam,queryWrapper);
        }

        String name=collegeQuery.getName();
        Integer level=collegeQuery.getLevel();
        String begin=collegeQuery.getJoinDateBegin();
        String end=collegeQuery.getJoinDateEnd();

        if (StringUtils.hasLength(name)){
            queryWrapper.likeRight("name",name);
        }
        if (level!=null){
            queryWrapper.eq("level",level);
        }
        if (StringUtils.hasLength(begin)){
            queryWrapper.ge("join_date",begin);
        }
        if (StringUtils.hasLength(end)){
            queryWrapper.le("join_date",end);
        }
        return baseMapper.selectPage(pageParam,queryWrapper);
    }
}
