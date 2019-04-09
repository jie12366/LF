package com.lingfei.admin.utils;

import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.User;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 自定义批量处理的工具类
 *
 * @author www.xyjz123.xyz
 * @date 2019/3/18 14:01
 */
public class Provide {

    /**
     * 批量增加
     *
     * @param map Map
     * @return 批量插入的Sql语句
     */
    public String batchAdd(Map map) {
        List<Announce> students = (List<Announce>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("insert into tb_user(phone,name,password) values");
        MessageFormat mf = new MessageFormat(
                "(#'{'list[{0}].phone},#'{'list[{0}].name},#'{'list[{0}].password})"
        );
        for (int i = 0; i < students.size(); i++) {
            sb.append(mf.format(new Object[]{i}));
            if (i < students.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    /**
     * 批量删除的模板
     *
     * @param map Map
     * @return 模板Sql
     */
    public String batchDelete(Map map) {
        List<Announce> students = (List<Announce>) map.get("list");
        StringBuffer sbs = new StringBuffer();
        for (int i = 0; i < students.size(); i++) {
            sbs.append("'").append(students.get(i).getId()).append("'");
            if (i < students.size() - 1) {
                sbs.append(",");
            }
        }
        sbs.append(")");
        return sbs.toString();
    }

    /**
     * 批量删除announce表
     *
     * @param maps Map
     * @return 批量删除的Sql语句
     */
    public String batchDeleteAnnounce(Map maps) {
        StringBuffer sb = new StringBuffer();
        sb.append("delete from announce where id in (");
        sb.append(batchDelete(maps));
        return sb.toString();
    }

    /**
     * 批量删除user表
     *
     * @param map Map
     * @return 批量删除的Sql语句
     */
    public String batchDeleteUser(Map map) {
        StringBuffer sb = new StringBuffer();
        sb.append("delete from user where id in (");
        sb.append(batchDelete(map));
        return sb.toString();
    }

    /**
     * 批量删除competition表
     *
     * @param map Map
     * @return 批量删除的sql语句
     */
    public String batchDeleteCompetition(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("delete from competition where id in (");
        stringBuilder.append(batchDelete(map));
        return stringBuilder.toString();
    }

    /**
     * 动态修改user表
     *
     * @param user User
     * @return 动态修改的Sql语句
     */
    public String dynamicStuUpd(User user) {
        return new SQL() {{
            UPDATE("user");
            if (user.getName() != null) {
                SET("name = #{name}");
            }
            if (user.getNumber() != null) {
                SET("number = #{number}");
            }
            if (user.getStuClass() != null) {
                SET("stuClass=#{stuClass}");
            }
            if (user.getQq() != null) {
                SET("qq = #{qq}");
            }
            if (user.getEmail() != null) {
                SET("email = #{email}");
            }
            if (user.getPhone() != null) {
                SET("phone = #{phone}");
            }
            if (user.getDepart() != null) {
                SET("depart = #{depart}");
            }
            WHERE("id =#{id}");
        }}.toString();
    }
}
