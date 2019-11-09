package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/6 23:14
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 取出所有的结果集
     *
     * @return List<User>
     */
    @Select("select * from user_info")
    List<UserInfo> listUser();

    /**
     * 根据名字模糊搜索
     * @param name 名字
     * @return
     */
    @Select("select * from user_info where depart like '%${_parameter}%'")
    List<UserInfo> getUserByName(String name);

    /**
     * 根据id 获取记录
     *
     * @param uuid
     * @return User
     */
    @Select("select * from user_info where uuid = #{uuid}")
    UserInfo getUser(String uuid);

    /**
     * 判断uuid是否存在
     * @param uuid 第三方登录uuid
     * @return 存在返回1，不存在返回0
     */
    @Select("select count(*) from user_info where uuid=#{uuid}")
    int isExistsUuid(String uuid);

    /**
     * 更新uuid
     * @param uid 用户id
     * @param uuid 第三方登录id
     * @return
     */
    @Update("update user_info set uuid=#{uuid} where uid=#{uid}")
    int updateUuid(int uid,String uuid);

    /**
     * 获取uuid
     * @param uid
     * @return
     */
    @Select("select uuid from user_info where uid=#{uid}")
    String getUuid(int uid);

    /**
     * 保存用户信息
     * @param orderCount int 约球次数
     * @param uuid       uuid 第三方登录id
     * @param uid       uid 用户id
     * @param headPath 头像路径
     * @return 是否更新成功
     */
    @Insert("insert into user_info(uid,uuid,headPath,orderCount) values(#{uid},#{uuid},#{headPath},#{orderCount})")
    int save(int uid,String uuid,String headPath,int orderCount);

    /**
     * 根据uuid更新头像
     * @param headPath 头像路径
     * @param uuid 用户id
     * @return 是否成功
     */
    @Update("update user_info set headPath=#{headPath} where uuid=#{uuid}")
    int updateHeadPath(String headPath, String uuid);

    /**
     * 根据id更新记录
     *
     * @param name     String 姓名
     * @param number   String 学号
     * @param sex      String 性别
     * @param stuClass String 班级
     * @param qq       String QQ号
     * @param email    String 邮箱
     * @param depart   String 部门
     * @param uuid       uuid
     * @return 是否更新成功
     */
    @Update("update user_info set name = #{name},number = #{number},sex=#{sex},stuClass = #{stuClass},qq = #{qq}," +
            "email = #{email},depart = #{depart} where uuid = #{uuid}")
    int updateUser(String name, String number,String sex, String stuClass, String qq, String email, String depart, String uuid);

    /**
     * 根据用户id更新约球次数
     *
     * @param count 次数
     * @param uuid      int
     * @return 更新是否成功
     */
    @Update("update user_info set orderCount = orderCount + #{count} where uuid = #{uuid}")
    int updateCount(int count, String uuid);

    /**
     * 根据id删除记录
     *
     * @param uuid int 序号
     * @return 是否删除成功
     */
    @Delete("delete from user_info where uuid = #{uuid}")
    int deleteUser(String uuid);
}