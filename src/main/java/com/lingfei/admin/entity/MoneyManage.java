package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/31 10:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "money_manage")
public class MoneyManage {

    @Column(name = "id", type = MySqlTypeConstant.INT, length = 10, isKey = true, isAutoIncrement = true)
    private int id;

    @Column(name = "reimburse", type = MySqlTypeConstant.VARCHAR, length = 30)
    private String reimburse;

    @Column(name = "deal", type = MySqlTypeConstant.VARCHAR, length = 30)
    private String deal;

    @Column(name = "content", type = MySqlTypeConstant.TEXT)
    private String content;

    @Column(name = "spend", type = MySqlTypeConstant.DOUBLE, length = 30)
    private float spend;

    @Column(name = "balance", type = MySqlTypeConstant.DOUBLE, length = 30)
    private float balance;
}