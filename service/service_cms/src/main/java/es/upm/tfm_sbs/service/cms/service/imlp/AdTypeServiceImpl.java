package es.upm.tfm_sbs.service.cms.service.imlp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.cms.entity.AdType;
import es.upm.tfm_sbs.service.cms.mapper.AdTypeMapper;
import es.upm.tfm_sbs.service.cms.service.AdTypeService;
import org.springframework.stereotype.Service;

@Service
public class AdTypeServiceImpl extends ServiceImpl<AdTypeMapper, AdType> implements AdTypeService {
}
