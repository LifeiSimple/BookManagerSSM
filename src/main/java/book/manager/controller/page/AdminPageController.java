package book.manager.controller.page;

import book.manager.entity.AuthInfo;
import book.manager.service.BookService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/page/admin")
public class AdminPageController {

    @Resource
    BookService bookService;


    @RequestMapping("/index")
    public String index(Model model){
        AuthInfo authInfo = new AuthInfo();
        // 向首页添加部分用户信息
        model.addAttribute("username", authInfo.getUsername());
        model.addAttribute("userrole", authInfo.getUserrole());

        return "admin/index";
    }

    @RequestMapping("/book")
    public String book(Model model){
        AuthInfo authInfo = new AuthInfo();
        model.addAttribute("username", authInfo.getUsername());
        model.addAttribute("userrole", authInfo.getUserrole());

        model.addAttribute("booklist", bookService.getAllBook());

        return "admin/book";
    }

    @RequestMapping("/add-book")
    public String addBook(Model model){
        AuthInfo authInfo = new AuthInfo();
        // 向首页添加部分用户信息
        model.addAttribute("username", authInfo.getUsername());
        model.addAttribute("userrole", authInfo.getUserrole());

        return "admin/add-book";
    }



}
