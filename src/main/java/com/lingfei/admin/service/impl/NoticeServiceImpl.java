package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.Notice;
import com.lingfei.admin.mapper.NoticeMapper;
import com.lingfei.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 10:33
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    @Qualifier("noticeMapper")
    private NoticeMapper noticeMapper;


    @Override
    public Optional<Notice> findById(String id) {
        return noticeMapper.findById(id);
    }

    @Override
    public Notice save(Notice blog) {
        return noticeMapper.save(blog);
    }

    @Override
    public void deleteById(String id){
        noticeMapper.deleteById(id);
    }

    @Override
    public void delete(Notice blog) {
        noticeMapper.delete(blog);
    }

    @Override
    public List<Notice> findAll() {
        return (List<Notice>) noticeMapper.findAll();
    }

    @Override
    public Page<Notice> findByName(String name, PageRequest pageRequest) {
        return noticeMapper.findByName(name,pageRequest);
    }

    @Override
    public Page<Notice> findByNumber(String title, PageRequest pageRequest) {
        return noticeMapper.findByNumber(title,pageRequest);
    }

}
