package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.OrderItem;
import com.lingfei.admin.mapper.OrderItemMapper;
import com.lingfei.admin.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 17:15
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    OrderItemMapper orderItemMapper;

    @Override
    public int saveItem(String uid, String msg) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return orderItemMapper.saveItem(uid,sdf.format(date),msg);
    }

    @Override
    public List<OrderItem> getItem(String uid) {
        return orderItemMapper.getItem(uid);
    }
}