package book.manager.config;

import book.manager.entity.AuthUser;
import book.manager.mapper.UserMapper;
import book.manager.service.impl.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    //继承WebSecurityConfigurerAdapter

    // 将 Token 信息存储在本地数据库
    @Resource
    PersistentTokenRepository repository;

    @Bean
    public PersistentTokenRepository jdbcRepository(@Autowired DataSource dataSource){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();  //使用基于JDBC的实现
        repository.setDataSource(dataSource);   //配置数据源
//        repository.setCreateTableOnStartup(true);   //启动时自动创建用于存储Token的表（建议第一次启动之后删除该行）
        return repository;
    }

//    @Resource
//    UserMapper userMapper;

    @Resource
    UserAuthService service;

    // 自定义登录界面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()    //配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/static/**","/page/auth/**", "/api/auth/**")
                    .permitAll()  //静态资源，使用permitAll来允许任何人访问
                .antMatchers("page/user/**","api/user/**")
                    .hasRole("user")
                .antMatchers("page/admin/**","api/admin/**")
                    .hasRole("admin")
                .anyRequest()
                    .hasAnyRole("user", "admin")     //所有请求必须登陆并且是user角色才可以访问（不包含上面的静态资源）


            .and()
                .formLogin()     //配置Form表单登陆
                .loginPage("/page/auth/login")    //登陆页面地址（GET）
                .loginProcessingUrl("/api/auth/login") //form表单提交地址（POST）
                .defaultSuccessUrl("/page/auth/index", true)
//                .successHandler(this::onAuthenticationSuccess)

            .and()
                .logout()
                .logoutUrl("/api/auth/logout")    //退出登陆的请求地址
                .logoutSuccessUrl("/login")    //退出后重定向的地址

            .and()
                .csrf()
                    .disable()
                .rememberMe()   //开启记住我功能
                .rememberMeParameter("remember")  //登陆请求表单中需要携带的参数，如果携带，那么本次登陆会被记住
                .tokenValiditySeconds(60 * 60 * 60 * 7)
                .tokenRepository(repository);
           /* .tokenRepository(new InMemoryTokenRepositoryImpl());  //这里使用的是直接在内存中保存的TokenRepository实现
            //TokenRepository有很多种实现，InMemoryTokenRepositoryImpl直接基于Map实现的，缺点就是占内存、服务器重启后记住我功能将失效
            //后面我们还会讲解如何使用数据库来持久化保存Token信息 */

    }


//    private void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        HttpSession session = httpServletRequest.getSession();
//        AuthUser user = userMapper.getPasswordByUsername(authentication.getName());
//        session.setAttribute("user", user);
//        httpServletResponse.sendRedirect("/bookmanager/index");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
        //这里使用SpringSecurity提供的BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        auth
                .inMemoryAuthentication() //直接验证方式，之后会讲解使用数据库验证
                .passwordEncoder(encoder) //密码加密器
                .withUser("test")   //用户名
                .password(encoder.encode("123456"))   //这里需要填写加密后的密码
                .roles("user");   //用户的角色
        */

        // 使用数据库中的密码
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
