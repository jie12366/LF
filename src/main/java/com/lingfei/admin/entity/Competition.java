package com.lingfei.admin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 12:16
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="competition")
public class Competition extends BaseModel implements Serializable {

    @Excel(name = "序号",orderNum = "0")
    @Column(name = "id",type = MySqlTypeConstant.INT,isKey = true,isAutoIncrement = true,length = 5)
    private int id;

    @Excel(name = "姓名",orderNum = "1")
    @Column(name = "name",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String  name;

    @Excel(name = "班级",orderNum = "2")
    @Column(name = "stuClass",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String  stuClass;

    @Excel(name = "QQ",orderNum = "3")
    @Column(name = "qq",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String  qq;

    @Excel(name = "手机号",orderNum = "4")
    @Column(name = "phone",type = MySqlTypeConstant.VARCHAR,length = 20)
    private String  phone;
}
