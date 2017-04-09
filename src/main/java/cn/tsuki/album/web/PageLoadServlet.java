package cn.tsuki.album.web;

import cn.tsuki.album.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tsuki on 2017/4/9.
 */
@WebServlet(urlPatterns="/pageLoad")
public class PageLoadServlet extends BaseServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        response.setContentType("text/javascript;charset=gbk");
        //获取输出流
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        String name = (String) session.getAttribute("curUser");
        //如果name不为null，表明用户已经登录
        if (name != null)
        {
            //隐藏id为noLogin的元素（用户登录面版）
            out.println("$('#noLogin').hide()");
            out.println("S('#hasLogin').show()");
            out.println("onLoadHandler();");
            String curImg = (String) session.getAttribute("curImg");
            if (curImg != null) {
                out.println("$('#show').attr('src', 'uploadfiles/"
                        + curImg + "');");
            }
        }
    }
}
