package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.CountVisitor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/21 22:01
 */
@Mapper
public interface VisitorMapper {

    /**
     * 插入
     * @param visitor int
     * @param  date String
     * @return
     */
    @Insert("insert into countv(visitor,date) values(#{visitor},#{date})")
    int saveVisitor(int visitor,String date);

    /**
     * 根据时间更新
     * @param visitor int
     * @param date String
     * @return
     */
    @Update("update countv set visitor = #{visitor} where date =#{date}")
    int updateVisitor(int visitor,String date);

    /**
     * 根据时间查询
     * @param date String
     * @return
     */
    @Select("select * from countv where date = #{date}")
    CountVisitor getVisitorByDate(String  date);

    /**
     * 查询总的访客数
     * @return
     */
    @Select("select sum(visitor) from countv")
    int getAllVisitor();
}