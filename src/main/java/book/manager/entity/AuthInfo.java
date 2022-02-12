package book.manager.entity;

import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Data
public class AuthInfo {
    String username;
    String userrole;

    public AuthInfo(){
        // 向首页添加部分用户信息
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        String userroles = String.valueOf(user.getAuthorities());
        String userrole = userroles.substring(1,userroles.length()-1);

        this.username = username;
        this.userrole = userrole;
    }

}
