package book.manager.service.impl;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService implements UserDetailsService {

    @Resource
    UserMapper mapper;


    @Override                             // 参数 s 为用户名称
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthUser authUser = mapper.getPasswordByUsername(s);  //从数据库根据用户名获取密码
        if (authUser == null)
            throw new UsernameNotFoundException("登录失败，用户名或密码错误！");

        //这里需要返回UserDetails，SpringSecurity会根据给定的信息进行比对
        return User
                .withUsername(authUser.getName())
                .password(authUser.getPassword())   //直接从数据库取的密码
                .roles(authUser.getRole())   //用户角色
                .build();
    }
}
