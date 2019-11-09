package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Activity;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Mapper
public interface ActivityMapper {

    /**
     * 插入数据
     *
     * @param content 字段content
     * @param date    字段date
     * @param picture 文件名
     * @return 插入是否成功
     */
    @Insert("insert into activity(title,zone,content,date,picture) values(#{title},#{zone},#{content},#{date},#{picture})")
    int save(String title, String zone, String content, String date, String picture);

    /**
     * 获取所有的结果集
     *
     * @return List<Announce>
     */
    @Select("select * from activity")
    List<Activity> getAllResult();

    /**
     * 根据id获取一条记录
     *
     * @param id 传入的id
     * @return com.lingfei.admin.entity.Announce
     */
    @Select("select * from activity where id = #{id}")
    Activity getAnnounceById(@Param("id") int id);

    /**
     * 根据id删除一条记录
     *
     * @param id int
     * @return 删除是否成功
     */
    @Delete("delete from activity where id = #{id}")
    int deleteAnnounce(@Param("id") int id);

    /**
     * 根据id更新记录
     *
     * @param content String
     * @param date    Timestamp
     * @param id      int
     * @param picture 文件名
     * @return 是否更新成功
     */
    @Update("update activity set title=#{title},zone=#{zone}, content = #{content},date = #{date},picture=#{picture} where id = #{id}")
    int updateAnnounce(String title,String zone,String content, String date, String picture,int id);
}
