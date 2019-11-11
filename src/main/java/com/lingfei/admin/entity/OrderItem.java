package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 17:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {

    @Column(name = "id", type = MySqlTypeConstant.INT, length = 10, isKey = true, isAutoIncrement = true)
    private int id;

    /**
     * 用户id
     */
    @Column(name = "uid", type = MySqlTypeConstant.VARCHAR,length = 50)
    private String uid;

    /**
     * 时间
     */
    @Column(name = "date", type = MySqlTypeConstant.VARCHAR)
    private String date;

    /**
     * 信息
     */
    @Column(name = "msg", type = MySqlTypeConstant.VARCHAR, length = 30)
    private String msg;
}