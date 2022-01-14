package es.upm.tfm_sbs.service.ucenter.conreoller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.service.ucenter.entity.vo.RegisterVo;
import es.upm.tfm_sbs.service.ucenter.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    private final MemberService memberService;

    @Autowired
    public ApiMemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.ok();
    }
}