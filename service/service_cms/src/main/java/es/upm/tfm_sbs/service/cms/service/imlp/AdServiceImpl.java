package es.upm.tfm_sbs.service.cms.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.cms.entity.Ad;
import es.upm.tfm_sbs.service.cms.entity.vo.AdVo;
import es.upm.tfm_sbs.service.cms.feign.OssFileService;
import es.upm.tfm_sbs.service.cms.mapper.AdMapper;
import es.upm.tfm_sbs.service.cms.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    private final OssFileService ossFileService;

    @Autowired
    public AdServiceImpl(OssFileService ossFileService) {
        this.ossFileService = ossFileService;
    }

    @Override
    public IPage<AdVo> selectPage(Long page, Long limit) {

        QueryWrapper<AdVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("a.type_id", "a.sort");

        Page<AdVo> pageParam = new Page<>(page, limit);

        List<AdVo> records = baseMapper.selectPageByQueryWrapper(pageParam, queryWrapper);
        pageParam.setRecords(records);
        return pageParam;
    }

    @Override
    public boolean removeAdImageById(String id) {
        Ad ad = baseMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmpty(imagesUrl)){
                //删除图片
                Result r = ossFileService.removeFile(imagesUrl);
                return r.getSuccess();
            }
        }
        return false;
    }
}
