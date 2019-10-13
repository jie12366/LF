package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.User;
import com.lingfei.admin.utils.Provide;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:00
 */
@Mapper
public interface UserMapper {

    /**
     * 取出所有的结果集
     *
     * @return List<User>
     */
    @Select("select * from user")
    List<User> listUser();

    /**
     * 根据名字模糊搜索
     * @param name 名字
     * @return
     */
    @Select("select * from user where depart like '%${_parameter}%'")
    List<User> getUserByName(String name);

    /**
     * 根据id 获取记录
     *
     * @param uuid
     * @return User
     */
    @Select("select * from user where uuid = #{uuid}")
    User getUser(String uuid);

    /**
     * 根据账号获取id
     *
     * @param account
     * @return
     */
    @Select("select uuid from user where account = #{account}")
    String getId(String account);

    /**
     * 根据用户名获取密码
     *
     * @param account 账号
     * @return 密码
     */
    @Select("select password from user where account = #{account}")
    String getPasswordByAccount(String account);

    /**
     * 根据邮箱获取密码
     * @param email 邮箱
     * @return
     */
    @Select("select password from user where email = #{email}")
    String getPasswordByEmail(String email);

    /**
     * 判断账号是否存在
     * @param account
     * @return 存在返回1，不存在返回0
     */
    @Select("select count(*) from user where account=#{account}")
    int isExistsAccount(String account);

    /**
     * 判断uuid是否存在
     * @param uuid 第三方登录uuid
     * @return 存在返回1，不存在返回0
     */
    @Select("select count(*) from user where uuid=#{uuid}")
    int isExistsUuid(String uuid);

    /**
     * 插入账号密码注册
     *
     * @param account  账号
     * @param password 密码
     * @return 是否注册成功
     */
    @Insert("insert into user(account,password) values(#{account},#{password})")
    int saveAccount(String account, String password);

    /**
     * 根据手机号重置密码
     * @param account    手机号
     * @param password
     * @return
     */
    @Update("update user set password = md5(#{password}) where account = #{account}")
    int resetPassword(String password, String account);

    /**
     * 根据id更新记录
     *
     * @param name     String 姓名
     * @param number   String 学号
     * @param stuClass String 班级
     * @param qq       String QQ号
     * @param email    String 邮箱
     * @param depart   String 部门
     * @param uuid       uuid
     * @return 是否更新成功
     */
    @Update("update user set name = #{name},number = #{number},stuClass = #{stuClass},qq = #{qq}," +
            "email = #{email},depart = #{depart} where uuid = #{uuid}")
    int updateUser(String name, String number, String stuClass, String qq, String email, String depart, String uuid);

    /**
     * 存入uuid
     * @param account 账号
     * @param uuid 第三方登录id
     * @return 是否成功
     */
    @Update("update user set uuid = #{uuid} where account = #{account}")
    int updateUuid(String account, String uuid);

    /**
     * 根据用户id更新约球次数
     *
     * @param count 次数
     * @param uuid      int
     * @return 更新是否成功
     */
    @Update("update user set orderCount = orderCount + #{count} where uuid = #{uuid}")
    int updateCount(int count, String uuid);

    /**
     * 动态修改user表
     *
     * @param user User
     * @return 是否修改成功
     */
    @UpdateProvider(type = Provide.class, method = "dynamicStuUpd")
    int updateDynamicUser(User user);

    /**
     * 根据id删除记录
     *
     * @param uuid int 序号
     * @return 是否删除成功
     */
    @Delete("delete from user where uuid = #{uuid}")
    int deleteUser(String uuid);

    /**
     * 删除一组数据
     *
     * @param users List<User>
     * @return 是否删除成功
     */
    @DeleteProvider(type = Provide.class, method = "batchDeleteUser")
    int batchDeleteUser(List<User> users);
}
