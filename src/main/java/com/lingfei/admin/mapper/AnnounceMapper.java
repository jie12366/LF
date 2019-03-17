package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Announce;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

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
}
