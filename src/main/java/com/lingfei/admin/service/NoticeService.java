package com.lingfei.admin.service;

import com.lingfei.admin.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 10:51
 */
@Service
public interface NoticeService {
    /**
     * 根据id获取结果
     * @param id
     * @return
     */
    Optional<Notice> findById(String id);

    /**
     * 增加记录
     * @param blog
     * @return
     */
    Notice save(Notice blog);

    /**
     * 根据id删除记录
     * @param id
     */
    void deleteById(String id);

    /**
     * 删除记录
     * @param blog
     */
    void delete(Notice blog);

    /**
     * 获取所有的结果集
     * @return
     */
    List<Notice> findAll();

    /**
     * 根据姓名查找结果
     * @param name
     * @param pageRequest
     * @return
     */
    Page<Notice> findByName(String name, PageRequest pageRequest);

    /**
     * 根据学号查找结果
     * @param number
     * @param pageRequest
     * @return
     */
    Page<Notice> findByNumber(String number, PageRequest pageRequest);
}
