package com.lingfei.admin.service;

import com.lingfei.admin.entity.User;
import org.springframework.stereotype.Service;
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
     * 返回所有结果
     *
     * @return List<User>
     */
    List<User> listUser();

    /**
     * 根据名字模糊搜索
     * @param name 名字
     * @return
     */
    List<User> getUserByName(String name);

    /**
     * 根据id返回结果
     *
     * @param id int
     * @return user
     */
    User getUser(int id);

    /**
     * 根据账号获取id
     *
     * @param account
     * @return
     */
    int getId(String account);

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
     * 根据邮箱重置密码
     *
     * @param email
     * @param password
     * @return
     */
    int resetPassword(String password, String email);

    /**
     * 根据id更新记录
     *
     * @param user User
     * @return 更新是否成功
     */
    int updateUser(User user);

    /**
     * 根据用户id更新余额
     *
     * @param user User
     * @return 更新是否成功
     */
    int updateBalance(User user);

    /**
     * 动态修改user表
     *
     * @param user User
     * @return 是否成功
     */
    int updateDynamicUser(User user);

    /**
     * 根据id删除记录
     *
     * @param id int
     * @return 删除是否成功
     */
    int deleteUser(int id);

    /**
     * 检查用户登录账号密码是否匹配
     *
     * @param account  账号
     * @param password 密码
     * @return 是否匹配
     */
    boolean checkLogin(String account, String password);

    /**
     * 根据用户名获取密码
     *
     * @param account 账号
     * @return 密码
     */
    String getPasswordByAccount(String account);

    /**
     * 根据邮箱获取密码
     *
     * @param email
     * @return
     */
    String getPasswordByEmail(String email);
}
