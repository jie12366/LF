package com.lingfei.admin.service;

import com.lingfei.admin.entity.User;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/9/24 21:21
 */
public interface OrderBallService {

    void startOrder();

    List<User> getList();

    List<User> getListByPriority();

    int order(String uid);

    int cancelOrder(String uid);

    /**
     * 管理员手动取消约球
     * @param uid 用户id
     * @return 是否成功
     */
    int cancelOrderByManager(String uid);

    void stopOrder();
}