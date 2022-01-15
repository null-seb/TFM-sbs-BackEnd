package es.upm.tfm_sbs.service.ucenter.conreoller.api;

import es.upm.tfm_sbs.common.base.result.Result;
import es.upm.tfm_sbs.common.base.result.ResultCode;
import es.upm.tfm_sbs.common.base.util.JwtInfo;
import es.upm.tfm_sbs.common.base.util.JwtUtils;
import es.upm.tfm_sbs.service.base.exception.SbsException;
import es.upm.tfm_sbs.service.ucenter.entity.vo.LoginVo;
import es.upm.tfm_sbs.service.ucenter.entity.vo.RegisterVo;
import es.upm.tfm_sbs.service.ucenter.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return Result.ok().data("token", token);
    }

    @GetMapping("get-login-info")
    public Result getLoginInfo(HttpServletRequest request){

        try{
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return Result.ok().data("userInfo", jwtInfo);
        }catch (Exception e){
            log.error("Failed to parse user information，" + e.getMessage());
            throw new SbsException(ResultCode.FETCH_USERINFO_ERROR);
        }
    }
}
