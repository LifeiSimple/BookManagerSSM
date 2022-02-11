package book.manager.service;

public interface AuthService {

    // 注册是否成功
    void register(String name, String sex, String grade, String password);

}
