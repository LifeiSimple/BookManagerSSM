package book.manager.controller.page;

import book.manager.entity.AuthInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


// 负责登录和注册页面的跳转
@Controller
@RequestMapping("/page/auth")
public class AuthPageController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register() {

        return "register";
    }

    @RequestMapping("/index")
    public String index(Model model){

        AuthInfo authInfo = new AuthInfo();

        if (authInfo.getUserrole().equals("ROLE_user"))
            return "redirect:/page/user/index";
        else return "redirect:/page/admin/index";
    }

}
