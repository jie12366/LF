package com.lingfei.admin.service;

import com.lingfei.admin.entity.Activity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface ActivityService {

    /**
     * 检测数据是否为空并插入数据
     *
     * @param activity com.lingfei.admin.entity.activity
     * @return 是否成功
     */
    int save(Activity activity);

    /**
     * 获取所有的结果集
     *
     * @return List<Announce>
     */
    List<Activity> getAllResult();

    /**
     * 根据id获取记录
     *
     * @param id int
     * @return Announce
     */
    Activity getAnnounceById(int id);

    /**
     * 根据id删除记录
     *
     * @param id int
     * @return 删除是否成功
     */
    int deleteAnnounce(int id);

    /**
     * 根据id更新记录
     *
     * @param activity Activity
     * @return 是否更新成功
     */
    int updateAnnounce(Activity activity);
}
