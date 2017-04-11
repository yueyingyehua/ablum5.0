package cn.tsuki.album.web;

import cn.tsuki.album.exception.AlbumException;
import cn.tsuki.album.web.base.BaseServlet;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by tsuki on 2017/4/9.
 */
@WebServlet(urlPatterns = "/proUpload")
public class ProUploadServlet extends BaseServlet {
    public void service(HttpServletRequest request, HttpServletResponse response)throws IOException,ServletException {
        Iterator iterator = null;
        String title = null;
        //构造一个待指定Zone对象的配置类
        Configuration configurationb = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(configurationb);
        //生成上传凭证，然后准备上传
        String accessKey = "MahDV0fCP-6EbdqpikxLgiz9D0er8AvrhNyvlDj6";
        String secretKey = "fkmPiJRDZkLuEMRwduvthTeHFkiV4FEYC_Dkflyv";
        String bucket = "album";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        response.setContentType("text/html;charset=gbk");
        System.out.println("上传文件");

        //获取输出流
        PrintWriter out = response.getWriter();
        try {
            //使用Uploader处理上传
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            List items = servletFileUpload.parseRequest(request);
            iterator = items.iterator();
            //遍历每个表单控件对应的内容
            while (iterator.hasNext()) {
                FileItem item = (FileItem) iterator.next();
                //如果该项是普通表单域
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    if (name.equals("title")) {
                        title = item.getString("gbk");
                    }
                }
                //如果是需要上传的文件
                else {
                    String user = (String) request.getSession()
                            .getAttribute("curUser");
                    System.out.println("uploadUser: " + user);
                    String serverFileName = null;
                    //返回文件名
                    String fileName = item.getName();
                    System.out.println("filename:" + fileName);
                    //取得文件后缀
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    System.out.println("suffix:" + suffix);
                    //返回文件类型
                    String contentType = item.getContentType();
                    System.out.println("contentType:" + contentType);

                    //只允许上传jpg、gif、png图片
                    if (contentType.equals("image/pjpeg")
                            || contentType.equals("image/gif")
                            || contentType.equals("image/jpeg")
                            || contentType.equals("image/png")) {

                        InputStream inputStream = item.getInputStream();
                        System.out.println("inpitStream: "+inputStream.toString());
                        serverFileName = UUID.randomUUID().toString() + suffix;
                      /*  String filepath = getServletContext().getRealPath("/")
                                + "uploadfiles\\" + serverFileName + suffix;*/
                        //七牛云
                        try {
                            Response response1 = uploadManager.put(inputStream, serverFileName, upToken, null, null);
                            //解析上传成功的结果
                            DefaultPutRet defaultPutRet = new Gson().fromJson(response1.bodyString(), DefaultPutRet.class);
                            System.out.println(defaultPutRet.key);
                        } catch (QiniuException e) {
                            Response r = e.response;
                            //响应的文本信息
                            System.out.println(r.bodyString());
                        }

                        inputStream.close();

                        albumService.addPhoto(user, title, serverFileName);
                        out.write("<script type='text/javascript'>" +
                                "parent.callback('恭喜你，文件上传成功！')" +
                                "</script>");

                    } else {
                        out.write("<script type='text/javascript'>" +
                                "parent.callback('本系统只允许上传" +
                                "JPG、GIF、PNG图片文件，请重试！')</script>");
                    }
                }
            }


        } catch (FileUploadException e) {
            e.printStackTrace();
            out.write("<script type='text/javascript'>" +
                    "parent.callback('处理上传文件出现错误，请重试！')" +
                    "</script>");
        } catch (AlbumException ex) {
            ex.printStackTrace();
        }
    }
}
