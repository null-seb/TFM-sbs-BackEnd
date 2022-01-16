package es.upm.tfm_sbs.service.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import es.upm.tfm_sbs.service.ucenter.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    Integer selectRegisterNumByDay(String day);
}
