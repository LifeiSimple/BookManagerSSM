package book.manager.mapper;

import book.manager.entity.AuthInfo;
import book.manager.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {
    @Select("select * from borrow")
    List<Borrow> borrowList();
}
