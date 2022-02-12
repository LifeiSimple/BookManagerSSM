package book.manager.mapper;


import book.manager.entity.Book;
import book.manager.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book")
    List<Book> allBook();


    @Results({
            @Result(column = "bid", property = "bid"),
            @Result(column = "title", property = "title"),
            @Result(column = "desc", property = "desc"),
            @Result(column = "price", property = "price")
    })
    @Select("SELECT book.bid,book.title,book.`desc`,book.price, student.sid " +
            "FROM book,borrow,student " +
            "WHERE book.bid = borrow.bid AND borrow.sid = student.sid AND student.sid = #{sid}")
    List<Book> borrowListBySid(int sid);

    @Delete("delete from book where bid = #{bid}")
    void deleteBook(int bid);

    @Insert("insert into book(title, `desc`, price) values(#{title}, #{desc}, #{price})")
    void addBook(@Param("title") String title, @Param("desc") String desc, @Param("price") double price);

    @Insert("insert into borrow(bid, sid, `time`) values(#{bid}, #{sid}, NOW())")
    void addBorrow(@Param("bid") int bid, @Param("sid") int sid);

    @Delete("delete from borrow where bid = #{bid} and sid = #{sid}")
    void returnBook(@Param("bid") int bid, @Param("sid") int sid);
}
