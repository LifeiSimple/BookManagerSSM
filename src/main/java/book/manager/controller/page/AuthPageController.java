package book.manager.controller.page;

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

        // 向首页添加部分用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userroles = String.valueOf(user.getAuthorities());
        String userrole = userroles.substring(1,userroles.length()-1);

        if (userrole.equals("ROLE_user"))
            return "redirect:/page/user/index";
        else return "redirect:/page/admin/index";
    }

}
