package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:00
 */
@Mapper
public interface UserMapper {

    /**
     * 根据账号获取id
     *
     * @param account 账号
     * @return
     */
    @Select("select * from user where account = #{account}")
    User getUserByAccount(String account);

    /**
     * 根据账号获取id
     *
     * @param id 用户id
     * @return
     */
    @Select("select * from user where id = #{id}")
    User getUserId(int id);

    /**
     * 判断账号是否存在
     * @param account
     * @return 存在返回1，不存在返回0
     */
    @Select("select count(*) from user where account=#{account}")
    int isExistsAccount(String account);

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
}
