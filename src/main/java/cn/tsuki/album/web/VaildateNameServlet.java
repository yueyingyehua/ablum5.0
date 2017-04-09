package cn.tsuki.album.web;

import cn.tsuki.album.exception.AlbumException;
import cn.tsuki.album.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by tsuki on 2017/4/9.
 */
@WebServlet(urlPatterns = "/validateName")
public class VaildateNameServlet extends BaseServlet{
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        String name = request.getParameter("user");
        response.setContentType("text/javascript;charset=gb2312");
        PrintWriter out = response.getWriter();
        try {
            if (name != null) {
                if (albumService.validateName(name)) {
                    out.println("alert('恭喜您，该用户名还未使用，拧可使用该用户名！');");
                    out.println("$('#user').val('');");
                }
            } else {
                out.println("alert('验证用户名出现异常，请更换用户名重试！');");
            }
        } catch (AlbumException ex) {
            out.println("alert('" + ex.getMessage() + "请更换用户名重试！');");
        }
    }
}
