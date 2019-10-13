package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.OrderUser;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.OrderBallService;
import com.lingfei.admin.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/9/24 21:22
 */
@Service
public class OrderBallServiceImpl implements OrderBallService {

    @Resource
    RedisTemplate<Object ,Object> redisTemplate;

    @Resource
    UserService userService;

    private final static String USERS = "users";

    private final static String START = "start";

    @Override
    public void startOrder() {
        // 清空之前的约球列表
        redisTemplate.delete("users");
        // 放入开始约球的标志
        redisTemplate.opsForValue().set(START,"start");
    }

    @Override
    public List<User> getList() {
        Map<Object,Object> users = redisTemplate.opsForHash().entries("users");
        List<User> userList = new ArrayList<>(12);
        if (users != null){
            // 遍历map集合，将对象放入list集合中
            for (Map.Entry<Object,Object> user: users.entrySet()){
                OrderUser orderUser = (OrderUser)user.getValue();
                userList.add(orderUser.getUser());
            }
            return userList;
        }
        return null;
    }

    @Override
    public List<User> getListByPriority() {
        Map<Object,Object> orderUsers = redisTemplate.opsForHash().entries("users");
        List<OrderUser> list =  new ArrayList<>(12);
        if (orderUsers != null){
            // 遍历map集合，将对象放入list集合中
            for (Map.Entry<Object,Object> user: orderUsers.entrySet()){
                list.add((OrderUser)user.getValue());
            }
        }
        // 根据指定的比较规则来排序集合
        Collections.sort(list);
        // 取出user
        List<User> users = new ArrayList<>(12);
        for (OrderUser orderUser : list){
            users.add(orderUser.getUser());
        }
        return users;
    }

    @Override
    public int order(String uid) {
        if (uid != null){
            if (redisTemplate.opsForValue().get(START) != null){
                User user = userService.getUser(uid);
                OrderUser orderUser = new OrderUser(uid,user,new Date());
                if(redisTemplate.opsForHash().get(USERS,uid) == null){
                    int res = userService.updateCount(1,uid);
                    if (res != 0){
                        redisTemplate.opsForHash().put(USERS,uid,orderUser);
                        return 1;
                    }
                }
                // 你已经约球了
                return 2;
            }
            // 约球还没开始
            return 3;
        }
        // 约球失败
        return 0;
    }

    @Override
    public int cancelOrder(String uid) {
        if (uid != null){
            if (redisTemplate.opsForValue().get(START) != null){
                if(redisTemplate.opsForHash().get(USERS,uid) != null){
                    int res = userService.updateCount(-1,uid);
                    if (res != 0){
                        redisTemplate.opsForHash().delete("users",uid);
                        return 1;
                    }
                }
                // 还没有约球
                return 2;
            }
            // 约球还没开始
            return 3;
        }
        // 取消约球失败
        return 0;
    }

    @Override
    public int cancelOrderByManager(String uid) {
        if (uid != null){
            // 约球次数减1
            int res = userService.updateCount(-1,uid);
            if (res != 0){
                redisTemplate.opsForHash().delete("users",uid);
                return 2;
            }
        }
        // 取消约球失败
        return 0;
    }

    @Override
    public void stopOrder() {
        // 清除约球的标志
        redisTemplate.delete(START);
    }
}