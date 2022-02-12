package book.manager.service;

import book.manager.entity.AuthInfo;
import book.manager.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();
    List<Book> getAllBookWithoutBorrow();
    List<Book> getAllBorrowedBookById(AuthInfo authInfo);
    void deleteBook(int bid);
    void addBook(String title, String desc, double price);
    void borrowBook(int bid, AuthInfo authInfo);
    void returnBook(int bid, AuthInfo authInfo);

}
