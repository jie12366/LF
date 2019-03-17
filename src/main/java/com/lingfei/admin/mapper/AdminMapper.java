package com.lingfei.admin.mapper;

import com.lingfei.admin.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Mapper
public interface AdminMapper {

    /**
     * 取出管理员的用户名和密码
     * @return 返回结果集
     */
    @Select("select userName,password from admin")
    Admin returnResult();
}
