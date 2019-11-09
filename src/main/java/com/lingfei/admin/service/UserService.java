package com.lingfei.admin.service;

import com.lingfei.admin.entity.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:46
 */

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface UserService {

    /**
     * 根据账号获取id
     *
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * 根据账号获取id
     *
     * @param id 用户id
     * @return
     */
    User getUserId(int id);

    /**
     * 判断账号是否存在
     * @param account 账号
     * @return 存在返回1，不存在返回0
     */
    int isExistsAccount(String account);

    /**
     * 插入数据，注册
     *
     * @param account  账号
     * @param password 密码
     * @return 是否插入成功
     */
    int saveAccount(String account, String password);

    /**
     * 检查用户登录账号密码是否匹配
     *
     * @param account  账号
     * @param password 密码
     * @return 是否匹配
     */
    boolean checkLogin(String account, String password);
}
