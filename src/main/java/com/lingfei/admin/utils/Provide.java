package com.lingfei.admin.utils;

import com.lingfei.admin.entity.Announce;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 自定义批量处理的工具类
 * @author www.xyjz123.xyz
 * @date 2019/3/18 14:01
 */
public class Provide{

    /**
     * 批量增加
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
     * 批量删除
     * @param map Map
     * @return 批量删除的Sql语句
     */
    public String batchDelete(Map map) {
        List<Announce> students = (List<Announce>) map.get("list");
        StringBuffer sb = new StringBuffer();
        sb.append("delete from announce where id in (");
        for (int i = 0; i < students.size(); i++) {
            sb.append("'").append(students.get(i).getId()).append("'");
            if (i < students.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
