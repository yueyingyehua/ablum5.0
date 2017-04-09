package cn.tsuki.album.web;

import cn.tsuki.album.exception.AlbumException;
import cn.tsuki.album.vo.PhotoHolder;
import cn.tsuki.album.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */
@WebServlet(urlPatterns = "/getPhoto")
public class GetPhotoServlet extends BaseServlet{
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException
    {
        HttpSession session = request.getSession(true);
        //从HttpSession中获取系统当前用户、相片列表的当前页码
        String name = (String) session.getAttribute("curUser");
        Object pageObj = session.getAttribute("curUser");
        //如果HttpSession中的curPage为null，则设置当前为第一页
        int curPage = pageObj == null ? 1 : (Integer) pageObj;
        response.setContentType("text/javascript;charset=gbk");
        //获取输出流
        PrintWriter out = response.getWriter();
        try {
            //?为什么这里可以使用name
            System.out.println("111");
            List<PhotoHolder> photos = albumService.getPhotoByUser(name, curPage);
            System.out.println("222");
            //清空id为list的元素
            out.println("var list = $('#list').empty();");
            for (PhotoHolder photoHolder : photos) {
                //将每个相片动态添加到id为list的元素中
                out.println("list.append(\"<div align='center'>" +
                        "<a href='javascript:void(0)' onclick=\\\"showImg('"
                        + photoHolder.getFileName() + "');\\\">"
                        + photoHolder.getTitle() + "</a></div>\")l");

            }
        } catch (AlbumException ex) {
            out.println("alert('" + ex.getMessage() + "请重试！')");
        }
    }
}
