package com.lingfei.admin.service;

import com.lingfei.admin.entity.CountVisitor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/21 22:33
 */

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

public interface VisitorService {

    /**
     * 插入
     *
     * @return
     */
    int saveVisitor();

    /**
     * 更新(+1)
     *
     * @return
     */
    int updateVisitor();

    /**
     * 获取某日的访客
     *
     * @return
     */
    CountVisitor getVisitorByDate();

    /**
     * 获取总的访客数
     *
     * @return
     */
    int getAllVisitor();

    /**
     * 获取当前时间
     *
     * @return
     */
    String getDate();
}
