package com.lingfei.admin.service;

import com.lingfei.admin.entity.UserInfo;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/7 9:42
 */
public interface UserInfoService {

    /**
     * 取出所有的结果集
     *
     * @return List<User>
     */
    List<UserInfo> listUser();

    /**
     * 根据名字模糊搜索
     * @param name 名字
     * @return
     */
    List<UserInfo> getUserByName(String name);

    /**
     * 根据id 获取记录
     *
     * @param uuid
     * @return User
     */
    UserInfo getUser(String uuid);

    /**
     * 判断uuid是否存在
     * @param uuid 第三方登录uuid
     * @return 存在返回1，不存在返回0
     */
    int isExistsUuid(String uuid);

    /**
     * 更新uuid
     * @param uid 用户id
     * @param uuid 第三方登录id
     * @return
     */
    int updateUuid(int uid,String uuid);

    /**
     * 根据uid获取uuid
     * @param uid 用户id
     * @return uuid
     */
    String getUuid(int uid);

    /**
     * 保存用户信息
     *
     * @param userInfo UserInfo
     * @return 是否更新成功
     */
    int save(UserInfo userInfo);

    /**
     * 根据id更新记录
     *
     * @param userInfo UserInfo
     * @return 是否更新成功
     */
    int updateUser(UserInfo userInfo);

    /**
     * 根据uuid更新头像
     * @param headPath 头像路径
     * @param uuid 用户id
     * @return 是否成功
     */
    int updateHeadPath(String headPath, String uuid);

    /**
     * 根据用户id更新约球次数
     *
     * @param count 次数
     * @param uuid      int
     * @return 更新是否成功
     */
    int updateCount(int count, String uuid);

    /**
     * 根据id删除记录
     *
     * @param uuid int 序号
     * @return 是否删除成功
     */
    int deleteUser(String uuid);
}