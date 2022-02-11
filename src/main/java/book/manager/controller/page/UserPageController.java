package book.manager.controller.page;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page/user")
public class UserPageController {

    @RequestMapping("/index")
    public String index(Model model){

        // 向首页添加部分用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userroles = String.valueOf(user.getAuthorities());
        String userrole = userroles.substring(1,userroles.length()-1);


        model.addAttribute("username", username);
        model.addAttribute("userrole", userrole);
        return "user/index";
    }

    @RequestMapping("/book")
    public String book(Model model){

        // 向首页添加部分用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userroles = String.valueOf(user.getAuthorities());
        String userrole = userroles.substring(1,userroles.length()-1);


        model.addAttribute("username", username);
        model.addAttribute("userrole", userrole);
        return "user/book";
    }

}
