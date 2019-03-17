package com.lingfei.admin.service;

import com.lingfei.admin.entity.Announce;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Service
public interface AnnounceService {

    /**
     * 检测数据是否为空并插入数据
     * @param announce com.lingfei.admin.entity.announce
     * @return 是否成功
     */
    int save(Announce announce);

    /**
     * 获取所有的结果集
     * @return List<Announce>
     */
    List<Announce> getAllResult();
}
