package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.utils.Provide;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 12:29
 */

@Mapper
public interface CompetitionMapper {

    /**
     * 插入数据
     *
     * @param name     姓名
     * @param stuClass 班级
     * @param qq       QQ
     * @param phone    手机号
     * @return 是否成功
     */
    @Insert("insert into competition(name,stuClass,qq,phone,item) values(#{name},#{stuClass},#{qq},#{phone},#{item})")
    int saveCompetition(String name, String stuClass, String qq, String phone,String item);

    /**
     * 根据id更新competition表
     *
     * @param name     姓名
     * @param stuClass 班级
     * @param qq       QQ
     * @param phone    手机号
     * @param id       int
     * @return 更新是否成功
     */
    @Update("update competition set name=#{name},stuClass=#{stuClass},qq=#{qq},phone=#{phone},item=#{item} where id = #{id}")
    int updateCompetition(String name, String stuClass, String qq, String phone,String item,int id);

    /**
     * 查询所有数据
     *
     * @return List<Competition>
     */
    @Select("select * from competition")
    List<Competition> listCompetition();

    /**
     * 根据id查询数据
     *
     * @param id int
     * @return Competition
     */
    @Select("select * from competition where id = #{id}")
    Competition getCompetitionById(int id);

    /**
     * 根据id删除记录
     *
     * @param id int
     * @return 删除是否成功
     */
    @Delete("delete from competition where id=#{id}")
    int deleteCompetition(int id);

    /**
     * 批量删除
     *
     * @param competitions Competition
     * @return 是否删除成功
     */
    @DeleteProvider(type = Provide.class, method = "batchDeleteCompetition")
    int batchDeleteCompetition(List<Competition> competitions);
}
