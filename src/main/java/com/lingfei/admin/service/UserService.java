package com.lingfei.admin.service;

import com.lingfei.admin.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:46
 */

@Service
public interface UserService{

    /**
     * 返回所有结果
     * @return List<User>
     */
    List<User> listUser();

    /**
     * 根据id返回结果
     * @param id int
     * @return user
     */
    User getUser(int id);

    /**
     * 插入数据
     * @param user user
     * @return 是否插入成功
     */
    int saveUser(User user);

    /**
     * 根据id更新记录
     * @param user User
     * @return 更新是否成功
     */
    int updateUser(User user);

    /**
     * 根据用户id更新余额
     * @param user User
     * @return 更新是否成功
     */
    int updateBalance(User user);

    /**
     * 动态修改user表
     * @param user User
     * @return 是否成功
     */
    int updateDynamicUser(User user);

    /**
     * 根据id删除记录
     * @param id int
     * @return 删除是否成功
     */
    int deleteUser(int id);

    /**
     * 删除一组数据
     * @param users List<User>
     * @return 删除是否成功
     */
    int batchDelete(List<User> users);
}
