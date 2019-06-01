package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.MoneyManage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/31 11:08
 */
@Mapper
public interface MoneyManageMapper {

    /**
     * 将花费的信息填入
     * @param reimburse 报销者
     * @param deal 处理者
     * @param content 内容
     * @param spend 花费金额
     * @param balance 余额
     * @param time 时间
     * @return
     */
    @Insert("insert into money_manage(reimburse,deal,content,spend,balance,time) " +
            "values(#{reimburse},#{deal},#{content},#{spend},#{balance},#{time})")
    int saveMoneyManage(String reimburse,String deal,String content,double spend,double balance,String time);

    /**
     * 列出所有数据
     * @return
     */
    @Select("select * from money_manage")
    List<MoneyManage> listMoneyManage();

    /**
     * 根据id获取一个数据
     * @param id 序号
     * @return
     */
    @Select("select * from money_manage where id=#{id}")
    MoneyManage getMoneyManageById(int id);

    /**
     * 获取刚刚插入数据的id值
     * @return
     */
    @Select("select max(id) from money_manage")
    int getMaxId();

    /**
     * 更新余额
     * @param balance
     * @param id
     * @return
     */
    @Update("update money_manage set balance=#{balance} where id=#{id}")
    int updateBalance(double balance,int id);

    /**
     * 更新数据
     * @param reimburse
     * @param deal
     * @param content
     * @param spend
     * @param id
     * @param time
     * @return
     */
    @Update("update money_manage set reimburse=#{reimburse},deal=#{deal},content=#{content}," +
            "spend=#{spend},time=#{time} where id=#{id}")
    int updateMoneyManage(String reimburse,String deal,String content,double spend,String time,int id);

    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    @Delete("delete from money_manage where id=#{id}")
    int deleteMoneyManage(int id);
}