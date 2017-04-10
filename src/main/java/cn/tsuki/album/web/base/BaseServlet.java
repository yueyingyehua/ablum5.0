package cn.tsuki.album.web.base;

import cn.tsuki.album.service.AlbumService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by tsuki on 2017/4/9.
 */
public class BaseServlet extends HttpServlet{
    protected AlbumService albumService;
    //定义初始化方法，获取Spring容器的引用
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        ApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        albumService = (AlbumService) context.getBean("AlbumService");
    }
}
