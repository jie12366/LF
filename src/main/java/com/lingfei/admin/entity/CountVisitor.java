package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/21 21:55
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="countv")
public class CountVisitor implements Serializable {

    @Column(name = "visitor",type = MySqlTypeConstant.INT,length = 6)
    private int visitor;

    @Column(name = "date",type = MySqlTypeConstant.VARCHAR)
    private String date;

}
