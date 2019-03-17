package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.mapper.AnnounceMapper;
import com.lingfei.admin.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Service
public class AnnounceServiceImpl implements AnnounceService {

    @Autowired
    AnnounceMapper announceMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int save(Announce announce){
        Date date = new Date();
        Timestamp date1 = new Timestamp(date.getTime());
        announce.setDate(date1);
        System.out.println(announce);
        if (announce != null){
            return announceMapper.save(announce.getContent(),announce.getDate());
        }
        return 0;
    }

    @Override
    public List<Announce> getAllResult(){
         return announceMapper.getAllResult();
    }
}
