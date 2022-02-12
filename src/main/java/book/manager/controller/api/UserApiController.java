package book.manager.controller.api;

import book.manager.entity.AuthInfo;
import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import book.manager.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/api/user")
public class UserApiController {

    @Resource
    BookService bookService;


    @RequestMapping(value = "/borrow-book", method = RequestMethod.GET)
    public String borrowBook(@RequestParam("id") int bid) {
        AuthInfo authInfo = new AuthInfo();

        bookService.borrowBook(bid, authInfo);

        return "redirect:/page/user/book";
    }

    @RequestMapping(value = "/return-book", method = RequestMethod.GET)
    public String returnBook(@RequestParam("id") int bid) {
        AuthInfo authInfo = new AuthInfo();

        bookService.returnBook(bid, authInfo);

        return "redirect:/page/user/book";
    }
}
