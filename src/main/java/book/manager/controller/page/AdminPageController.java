package book.manager.controller.page;

import book.manager.entity.AuthInfo;
import book.manager.mapper.BookMapper;
import book.manager.mapper.BorrowMapper;
import book.manager.mapper.UserMapper;
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

    @Resource
    BorrowMapper borrowMapper;

    @Resource
    BookMapper bookMapper;

    @Resource
    UserMapper userMapper;

    @RequestMapping("/index")
    public String index(Model model){
        AuthInfo authInfo = new AuthInfo();
        // 向首页添加部分用户信息
        model.addAttribute("username", authInfo.getUsername());
        model.addAttribute("userrole", authInfo.getUserrole());
        model.addAttribute("borrowlist", borrowMapper.borrowDetialList());
        model.addAttribute("student_count", userMapper.getAllStudent());
        model.addAttribute("book_count", bookMapper.allBook().size());
        model.addAttribute("borrow_list", borrowMapper.borrowDetialList());

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
