package cn.tsuki.album.web;

import cn.tsuki.album.exception.AlbumException;
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
@WebServlet(urlPatterns = "/proLogin")
public class ProLoginServlet extends BaseServlet{
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException
    {
        String name = request.getParameter("user");
        String password = request.getParameter("password");
        System.out.println("proLogin: user:"+name+"password:"+password);
        response.setContentType("text/javascript;charset=gbk");
        //获取输出流
        PrintWriter out = response.getWriter();
        try{
            //清空id为user、password输入框的内容
            out.println("$('#user,#password').val('');");
            if (name != null && password != null && albumService.userLogin(name, password)) {
                HttpSession session = request.getSession(true);
                session.setAttribute("curUser", name);
                out.println("alert('您已经登录成功！')");
                out.println("$(\"#noLogin\").hide(500);");
                out.println("$(\"#hasLogin\").show(500);");
                //调用获取相片列表的方法
                out.println("onLoadHandler();");
            }else{
                out.println("alert('您输入的用户名、密码不符合，请重试！')");

            }
        }catch (AlbumException ex)
        {
            out.println("alert('" + ex.getMessage()
                    + "请更换用户名、密码重试！')");
        }
    }
}
