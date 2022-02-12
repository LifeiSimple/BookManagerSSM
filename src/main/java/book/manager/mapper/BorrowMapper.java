package book.manager.mapper;

import book.manager.entity.AuthInfo;
import book.manager.entity.Borrow;
import book.manager.entity.BorrowDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {
    @Select("select * from borrow")
    List<Borrow> borrowList();


    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "title", property = "book_title"),
            @Result(column = "name", property = "user_name"),
            @Result(column = "time", property = "time"),
    })
    @Select("SELECT borrow.id,book.title,borrow.time,student.`name` " +
            "FROM book,borrow,student " +
            "WHERE book.bid = borrow.bid AND borrow.sid = student.sid;")
    List<BorrowDetail> borrowDetialList();
}
