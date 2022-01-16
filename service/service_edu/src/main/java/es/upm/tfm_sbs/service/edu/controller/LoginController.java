package es.upm.tfm_sbs.service.edu.controller;

import es.upm.tfm_sbs.common.base.result.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {


    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    public Result info() {
        return Result.ok()
                .data("roles","[admin]")
                .data("name","admin")
                .data("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }
}
