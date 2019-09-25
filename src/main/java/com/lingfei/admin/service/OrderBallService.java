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

    int order(int uid);

    int cancelOrder(int uid);

    void stopOrder();
}