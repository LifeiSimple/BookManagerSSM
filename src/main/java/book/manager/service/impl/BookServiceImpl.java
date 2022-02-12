package book.manager.service.impl;

import book.manager.entity.AuthInfo;
import book.manager.entity.AuthUser;
import book.manager.entity.Book;
import book.manager.entity.Borrow;
import book.manager.mapper.BookMapper;
import book.manager.mapper.BorrowMapper;
import book.manager.mapper.UserMapper;
import book.manager.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookServiceImpl implements BookService {

    @Resource
    BookMapper bookMapper;
    @Resource
    UserMapper userMapper;
    @Resource
    BorrowMapper borrowMapper;

    @Override
    public List<Book> getAllBook() {
        return bookMapper.allBook();
    }

    @Override
    public List<Book> getAllBookWithoutBorrow() {
        List<Book> books = bookMapper.allBook();
        List<Borrow> borrows = borrowMapper.borrowList();
        List<Integer> book_borrowed = borrows
                .stream()
                .map(Borrow::getBid)
                .collect(Collectors.toList());
        books = books
                .stream()
                .filter(book -> !book_borrowed.contains(book.getBid()))
                .collect(Collectors.toList());
        return books;
    }

    @Override
    public List<Book> getAllBorrowedBookById(AuthInfo authInfo) {
        AuthUser auth = userMapper.getPasswordByUsername(authInfo.getUsername());
        int sid = userMapper.getSidByUserName(auth.getName());
        List<Book> booklist = bookMapper.borrowListBySid(sid);
        return booklist;
    }

    @Override
    public void deleteBook(int bid) {
        bookMapper.deleteBook(bid);
    }

    @Override
    public void addBook(String title, String desc, double price) {
        bookMapper.addBook(title, desc, price);
    }

    @Override
    public void borrowBook(int bid, AuthInfo authInfo) {

        int userid = userMapper.getSidByUserName(authInfo.getUsername());

        bookMapper.addBorrow(bid, userid);
    }

    @Override
    public void returnBook(int bid, AuthInfo authInfo) {
        int userid = userMapper.getSidByUserName(authInfo.getUsername());

        bookMapper.returnBook(bid, userid);
    }
}
