package cn.tsuki.album.dao.impl;

import cn.tsuki.album.dao.PhotoDao;
import cn.tsuki.album.domain.Photo;
import cn.tsuki.album.domain.User;
import cn.tsuki.common.dao.impl.BaseDaoHibernate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */

public class PhotoDaoHibernate extends BaseDaoHibernate<Photo> implements PhotoDao {
    /**
     * 查询属于指定用户的相片，进行分页控制
     * @param user  查询相片所属的用户
     * @param pageNo 需要查询的指定页
     * @return  查询到的相片
     */
    @Transactional
    public List<Photo> findByUser(User user, int pageNo) {
        return (List<Photo>) findByPage("select b from Photo b where "
                + "b.user = ?0", pageNo, PAGE_SIZE, user);

    }
}
