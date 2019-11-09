package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 17:11
 */
@Mapper
public interface OrderItemMapper {

    /**
     * 存入记录
     * @param date
     * @param msg
     * @return
     */
    @Insert("insert into order_item(uid,date,msg) values(#{uid},#{date},#{msg})")
    int saveItem(String uid,Date date, String msg);

    /**
     * 获取记录
     * @param uid
     * @return
     */
    @Select("select * from order_item where uid=#{uid} order by date desc")
    List<OrderItem> getItem(String uid);
}