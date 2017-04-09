package cn.tsuki.album.service;

import cn.tsuki.album.vo.PhotoHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */
@Transactional
public interface AlbumService {
    /**
     * 验证用户登录是否成功
     * @param name 登录的用户名
     * @param password 登录的密码
     * @return 用户登录发结果，成功返回true，否则返回false
     */
    boolean userLogin(String name, String password);

    /**
     * 注册新用户
     * @param name 新注册用户的用户名
     * @param password 新注册用户的密码
     * @return 新注册用户的主键
     */
    int registUser(String name, String password);

    /**
     * 添加照片
     * @param user 添加相片的用户
     * @param title 添加相片的标题
     * @param fileName 新添加相片的主键
     * @return 新添加相片的主键
     */
    int addPhoto(String user, String title, String fileName);

    /**
     * 根据用户获取该用户所以相片
     * @param user  当前用户
     * @param pageNo 页码
     * @return 返回属于该用户指定页的相片
     */
    List<PhotoHolder> getPhotoByUser(String user, int pageNo);

    /**
     * 验证用户名是否可用， 即数据库里是否已经存在该用户名
     * @param name 需要校验的用户名
     * @return 如果该用户名可以用，返回true，否则返回false
     */
    boolean validateName(String name);
}
