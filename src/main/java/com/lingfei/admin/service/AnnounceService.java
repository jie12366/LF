package com.lingfei.admin.service;

import com.lingfei.admin.entity.Announce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface AnnounceService {

    /**
     * 检测数据是否为空并插入数据
     *
     * @param announce com.lingfei.admin.entity.announce
     * @return 是否成功
     */
    int save(Announce announce);

    /**
     * 获取所有的结果集
     *
     * @return List<Announce>
     */
    List<Announce> getAllResult();

    /**
     * 根据id获取记录
     *
     * @param id int
     * @return Announce
     */
    Announce getAnnounceById(int id);

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
     * @param announce Announce
     * @return 是否更新成功
     */
    int updateAnnounce(Announce announce);

    /**
     * 批量删除
     *
     * @param announces 传入的一组id
     * @return 是否成功
     */
    int batchDelete(List<Announce> announces);

    /**
     * 群发邮件的接口
     *
     * @param tos     群发对象
     * @param theme   邮件主题
     * @param content 邮件内容
     */
    void sendEmail(String theme, String content, String... tos);
}
