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
@WebServlet(urlPatterns = "/showImg")
public class ShowImgServlet extends BaseServlet{
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        String img = request.getParameter("img");
        HttpSession session = request.getSession(true);
        //将用户正在浏览的图片放入HttpSession中
        session.setAttribute("curImg", img);
        response.setContentType("text/javascript;charset=gbk");
        //获取输出流
        PrintWriter out = response.getWriter();
        out.println("$('#show').attr('src', 'uploadfiles/" + img + "');");
    }
}
