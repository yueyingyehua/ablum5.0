package cn.tsuki.album.web;

import cn.tsuki.album.web.base.BaseServlet;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;

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
        //生成上传凭证，然后准备上传
        String accessKey = "MahDV0fCP-6EbdqpikxLgiz9D0er8AvrhNyvlDj6";
        String secretKey = "fkmPiJRDZkLuEMRwduvthTeHFkiV4FEYC_Dkflyv";
        String bucket = "album";
        Auth auth = Auth.create(accessKey, secretKey);
        Zone zone = Zone.zone0();
        Configuration configuration = new Configuration(zone);

        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth, configuration);
        String url = "http://oo8ecv0l9.bkt.clouddn.com/" + img;

        HttpSession session = request.getSession(true);
        //将用户正在浏览的图片放入HttpSession中
        session.setAttribute("curImg", img);
        response.setContentType("text/javascript;charset=gbk");
        //获取输出流
        PrintWriter out = response.getWriter();
        out.println("$('#show').attr('src', '" + url + "');");
    }
}
