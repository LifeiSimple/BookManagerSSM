package book.manager.mapper;

import book.manager.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{username}")
    AuthUser getPasswordByUsername(String username);

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id") // 返回 id
    @Insert("insert into users(name, role, password) values(#{name}, #{role}, #{password})")
    int registerUser(AuthUser user);

    @Insert("insert into student(uid, name, sex, grade) values(#{uid}, #{name}, #{sex}, #{grade})")
    int addStudentInfo(@Param("uid") int uid, @Param("name") String name, @Param("sex") String sex, @Param("grade") String grade);

    @Select("select sid from student where name = #{username}")
    int getSidByUserName(String username);

}
