package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.utils.Provide;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Mapper
public interface AnnounceMapper {

    /**
     * 插入数据
     * @param content 字段content
     * @param date 字段date
     * @return 插入是否成功
     */
    @Insert("insert into announce(content,date) values(#{content},#{date})")
    int save(@Param("content") String content, @Param("date") Timestamp date);

    /**
     * 获取所有的结果集
     * @return List<Announce>
     */
    @Select("select * from announce")
    List<Announce> getAllResult();

    /**
     * 根据id获取一条记录
     * @param id 传入的id
     * @return com.lingfei.admin.entity.Announce
     */
    @Select("select * from announce where id = #{id}")
    Announce getAnnounceById(@Param("id") int id);

    /**
     * 根据id删除一条记录
     * @param id int
     * @return 删除是否成功
     */
    @Delete("delete from announce where id = #{id}")
    int deleteAnnounce(@Param("id") int id);

    /**
     * 批量删除
     * @param announces 一组记录
     * @return 是否成功
     */
    @DeleteProvider(type = Provide.class,method = "batchDeleteAnnounce")
    int batchDeleteAnnounce(List<Announce> announces);

    /**
     * 根据id更新记录
     * @param content String
     * @param date Timestamp
     * @param id int
     * @return 是否更新成功
     */
    @Update("update announce set content = #{content},date = #{date} where id = #{id}")
    int updateAnnounce(String content,Timestamp date,int id);
}
