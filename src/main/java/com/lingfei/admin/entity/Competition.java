package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 12:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "competition")
public class Competition{

    @Column(name = "id", type = MySqlTypeConstant.INT, isKey = true, isAutoIncrement = true, length = 5)
    private int id;

    @Column(name = "aid", type = MySqlTypeConstant.INT, length = 20)
    private int aid;

    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String name;

    @Column(name = "stuClass", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String stuClass;

    @Column(name = "number", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String number;

    @Column(name = "qq", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String qq;

    @Column(name = "phone", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String phone;

    @Column(name = "item", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String item;

    public Competition(int aid, String name, String stuClass, String number, String qq, String phone, String item) {
        this.aid = aid;
        this.name = name;
        this.stuClass = stuClass;
        this.number = number;
        this.qq = qq;
        this.phone = phone;
        this.item = item;
    }
}
