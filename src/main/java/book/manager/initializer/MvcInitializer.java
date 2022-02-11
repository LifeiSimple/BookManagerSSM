package book.manager.initializer;

import book.manager.config.MvcConfiguration;
import book.manager.config.RootConfiguration;
import book.manager.config.SecurityConfiguration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfiguration.class, SecurityConfiguration.class}; //基本的Spring配置类，一般用于业务层配置
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfiguration.class}; //配置DispatcherServlet的配置类、主要用于Controller等配置
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //匹配路径，与上面一致
    }

    // 解决 controller 接收到的数据的编码问题
//    @Override
//    protected Filter[] getServletFilters() {
//        return new Filter[]{new CharacterEncodingFilter("UTF-8", true)};
//    }
}