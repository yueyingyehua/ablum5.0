package cn.tsuki.album.dao;

import cn.tsuki.album.domain.Photo;
import cn.tsuki.album.domain.User;
import cn.tsuki.common.dao.BaseDao;

import java.util.List;

/**
 * Created by tsuki on 2017/4/9.
 */
public interface PhotoDao extends BaseDao<Photo>{
    //以常量控制每页显示的相片数
    final int  PAGE_SIZE = 3;


    /**
     * 查询属于指定用户的相片，且进行分页控制
     * @param user  查询相片所属的用户
     * @param pageNo 需要查询的指定页
     * @return 查询到的相片
     */

    List<Photo> findByUser(User user, int pageNo);
}
