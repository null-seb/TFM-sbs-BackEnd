package es.upm.tfm_sbs.service.cms.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.cms.entity.Ad;
import es.upm.tfm_sbs.service.cms.entity.vo.AdVo;

public interface AdService  extends IService<Ad> {
    IPage<AdVo> selectPage(Long page, Long limit);

    boolean removeAdImageById(String id);
}
