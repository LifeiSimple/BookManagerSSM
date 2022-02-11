package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    UserMapper userMapper;

    @Transactional
    @Override
    public void register(String name, String sex, String grade, String password) {
        // 密码要先加密，加密后再存储在数据库中
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);

        AuthUser user = new AuthUser(0, name, password, "user");

        if (userMapper.registerUser(user) <= 0)
            throw new RuntimeException("用户基本信息添加失败!");
        if (userMapper.addStudentInfo(user.getId(), name, sex, grade) <=0)
            throw new RuntimeException("学生详细信息插入失败");
    }
}
