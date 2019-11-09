package com.lingfei.admin.service;

import com.lingfei.admin.entity.OrderItem;

import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 17:14
 */
public interface OrderItemService {

    /**
     * 存入记录
     * @param date
     * @param msg
     * @return
     */
    int saveItem(String uid, Date date, String msg);

    /**
     * 获取记录
     * @param uid
     * @return
     */
    List<OrderItem> getItem(String uid);
}