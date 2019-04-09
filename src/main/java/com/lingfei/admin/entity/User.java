package com.lingfei.admin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 20:47
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {

    @Column(name = "id", type = MySqlTypeConstant.INT, length = 10, isKey = true, isAutoIncrement = true)
    @Excel(name = "序号", orderNum = "0")
    private int id;

    @Column(name = "account", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String account;

    @Column(name = "password", type = MySqlTypeConstant.VARCHAR, length = 33)
    private String password;

    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "姓名", orderNum = "1")
    private String name;

    @Column(name = "number", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "学号", orderNum = "2")
    private String number;

    @Column(name = "stuClass", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "班级", orderNum = "3")
    private String stuClass;

    @Column(name = "qq", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "QQ", orderNum = "4")
    private String qq;

    @Column(name = "email", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "邮箱", orderNum = "5")
    private String email;

    @Column(name = "phone", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "手机号", orderNum = "6")
    private String phone;

    @Column(name = "depart", type = MySqlTypeConstant.VARCHAR, length = 20)
    @Excel(name = "部门", orderNum = "7")
    private String depart;

    @Column(name = "balance", type = MySqlTypeConstant.DOUBLE, length = 10)
    @Excel(name = "余额", orderNum = "8")
    private double balance;
}
