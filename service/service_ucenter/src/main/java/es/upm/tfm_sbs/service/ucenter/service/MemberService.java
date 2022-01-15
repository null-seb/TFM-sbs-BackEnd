package es.upm.tfm_sbs.service.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import es.upm.tfm_sbs.service.ucenter.entity.Member;
import es.upm.tfm_sbs.service.ucenter.entity.vo.LoginVo;
import es.upm.tfm_sbs.service.ucenter.entity.vo.RegisterVo;

public interface MemberService extends IService<Member> {
    void register(RegisterVo registerVo);
    String login(LoginVo loginVo);
}
