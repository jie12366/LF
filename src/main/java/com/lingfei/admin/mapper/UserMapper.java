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
     * @return List<User>
     */
    @Select("select * from user")
    List<User> listUser();

    /**
     * 根据id 获取记录
     * @param id
     * @return User
     */
    @Select("select * from user where id = #{id}")
    User getUser(int id);

    /**
     * 向user表中插入数据
     * @param name String 姓名
     * @param number String 学号
     * @param stuClass String 班级
     * @param qq String QQ号
     * @param email String  邮箱
     * @param phone String 手机号
     * @param depart String 部门
     * @return 是否插入成功
     */
    @Insert("insert into user(name,number,stuClass,qq,email,phone,depart,balance) values(#{name},#{number},#{stuClass},#{qq},#{email},#{phone},#{depart},0)")
    int saveUser(@Param("name") String name,@Param("number") String number,@Param("stuClass") String stuClass,@Param("qq") String qq,@Param("email") String email,@Param("phone") String phone,@Param("depart") String depart);

    /**
     * 根据id更新记录
     * @param name String 姓名
     * @param number String 学号
     * @param stuClass String 班级
     * @param qq String QQ号
     * @param email String 邮箱
     * @param phone String 手机号
     * @param depart String 部门
     * @param id int 序号
     * @return 是否更新成功
     */
    @Update("update user set name = #{name},number = #{number},stuClass = #{stuClass},qq = #{qq},email = #{email},phone = #{phone},depart = #{depart} where id = #{id}")
    int updateUser(String name,String number,String stuClass,String qq,String email,String phone,String depart,int id);

    /**
     * 根据用户id更新余额
     * @param balance Double
     * @param id int
     * @return 更新是否成功
     */
    @Update("update user set balance = #{balance} where id = #{id}")
    int updateBalance(double balance,int id);

    /**
     * 动态修改user表
     * @param user User
     * @return 是否修改成功
     */
    @UpdateProvider(type = Provide.class,method = "dynamicStuUpd")
    int updateDynamicUser(User user);

    /**
     * 根据id删除记录
     * @param id int 序号
     * @return 是否删除成功
     */
    @Delete("delete from user where id = #{id}")
    int deleteUser(int id);

    /**
     * 删除一组数据
     * @param users List<User>
     * @return 是否删除成功
     */
    @DeleteProvider(type = Provide.class,method = "batchDeleteUser")
    int batchDeleteUser(List<User> users);
}
