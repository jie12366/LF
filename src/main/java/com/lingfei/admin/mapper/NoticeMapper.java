package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 0:19
 */
@Component
public interface NoticeMapper extends ElasticsearchRepository<Notice, String>{

    /**
     * 根据姓名查找并分页
     * @param name 姓名
     * @param pageable
     * @return
     */
    Page<Notice> findByName(String name, Pageable pageable);

    /**
     * 根据学号查找并分页
     * @param number
     * @param pageable
     * @return
     */
    Page<Notice> findByNumber(String number, Pageable pageable);
}
