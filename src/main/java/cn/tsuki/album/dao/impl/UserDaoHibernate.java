package cn.tsuki.album.dao.impl;

import cn.tsuki.album.dao.UserDao;
import cn.tsuki.album.domain.User;
import cn.tsuki.common.dao.impl.BaseDaoHibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */

public class UserDaoHibernate extends BaseDaoHibernate<User> implements UserDao {
    /**
     * 根据用户名查找用户
     * @param name 需要查找的用户的用户名
     * @return 查找到的用户
     */
    @Transactional
    public User findByName(String name) {
        List<User> users = find("select u from User u where u.name = ?0", name);
        if (users != null && users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

}
