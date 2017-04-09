package cn.tsuki.album.service.impl;

import cn.tsuki.album.dao.PhotoDao;
import cn.tsuki.album.dao.UserDao;
import cn.tsuki.album.domain.Photo;
import cn.tsuki.album.domain.User;
import cn.tsuki.album.exception.AlbumException;
import cn.tsuki.album.service.AlbumService;
import cn.tsuki.album.vo.PhotoHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */
@Transactional
public class AlbumServiceImpl implements AlbumService {
    //业务逻辑组件所依赖的两个DAO组件
    private UserDao userDao = null;
    private PhotoDao photoDao = null;
    //依赖注入2个DAO组件所需的setter方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setPhotoDao(PhotoDao photoDao) {
        this.photoDao = photoDao;
    }

    /**
     * 验证用户登录是否成功
     * @param name 登录的用户名
     * @param password 登录的密码
     * @return 用户登录发结果，成功返回true，否则返回false
     */
    public boolean userLogin(String name, String password) {
        try {
            //使用UserDao根据用户名查询用户
            User user = userDao.findByName(name);
            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AlbumException("处理用户登录出现异常！");
        }
    }

    /**
     * 注册新用户
     * @param name 新注册用户的用户名
     * @param password 新注册用户的密码
     * @return 新注册用户的主键
     */
    public int registUser(String name, String password) {
        try {
            //创建一个新的User实例
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            //持久化User对象
            userDao.save(user);
            return user.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AlbumException("新用户注册出现异常！");
        }
    }

    /**
     * 添加照片
     * @param user 添加相片的用户
     * @param title 添加相片的标题
     * @param fileName 新添加相片的主键
     * @return 新添加相片的主键
     */
    public int addPhoto(String user, String title, String fileName) {
        try {
            //创建一个新的Photo实例
            Photo photo = new Photo();
            photo.setTitle(title);
            photo.setFileName(fileName);
            photo.setUser(userDao.findByName(user));
            //持久化Photo实例
            photoDao.save(photo);
            return photo.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AlbumException("添加相片过程出现异常！");
        }

    }

    /**
     * 根据用户获取该用户的所以相片
     * @param user  当前用户
     * @param pageNo 页码
     * @return 返回属于该用户指定页的相片
     */
    public List<PhotoHolder> getPhotoByUser(String user, int pageNo) {
        try {
            List<Photo> photoList = photoDao.findByUser(userDao.findByName(user), pageNo);
            List<PhotoHolder> photoHolders = new ArrayList<PhotoHolder>();
            for (Photo photo : photoList) {
                photoHolders.add(new PhotoHolder(photo.getTitle(), photo.getFileName()));

            }
            return photoHolders;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AlbumException("查询相片列表的过程出现异常！");
        }
    }

    /**
     * 验证用户名是否可用，即数据库里是否已经存在该用户名
     * @param name 需要校验的用户名
     * @return 如果该用户名可以，返回true，否则返回false
     */
    public boolean validateName(String name) {
        try {
            //根据用户名查询对应的User实例
            User user = userDao.findByName(name);
            if (user != null) {
                return false;
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AlbumException("验证用户名是否存在的过程中出现异常！");
        }
    }
}
