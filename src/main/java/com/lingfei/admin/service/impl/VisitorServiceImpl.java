package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.CountVisitor;
import com.lingfei.admin.mapper.VisitorMapper;
import com.lingfei.admin.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/21 22:36
 */

@Service
@CacheConfig
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    @Cacheable("visitor")
    public int saveVisitor(){
        visitorMapper.saveVisitor(1,this.getDate());
        return 0;
    }

    @Override
    @CachePut("visitor")
    public int updateVisitor(){
        int visitor = this.getVisitorByDate().getVisitor();
        return visitorMapper.updateVisitor(visitor + 1,this.getDate());
    }

    @Override
    public CountVisitor getVisitorByDate(){
        return visitorMapper.getVisitorByDate(this.getDate());
    }

    @Override
    @Cacheable(value = "visitor",key = "0")
    public int getAllVisitor(){
        return visitorMapper.getAllVisitor();
    }

    @Override
    public String getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
