package cn.tsuki.album.dao;

import cn.tsuki.album.domain.User;
import cn.tsuki.common.dao.BaseDao;

/**
 * Created by tsuki on 2017/4/2.
 */
public interface UserDao extends BaseDao<User>{
    /**
     * 根据用户名查找用户
     * @param name 需要查找的用户的用户名
     * @return 查找到的用户
     */
    User findByName(String name);
}
