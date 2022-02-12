package book.manager.controller.api;

import book.manager.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


// 用户注册
@Controller
@RequestMapping("/api/auth")
public class AuthApiController {

    @Resource
    AuthService authService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("sex") String sex,
                           @RequestParam("grade") String grade){


        authService.register(username, sex, grade, password);
        return "redirect:/login";
    }


}
