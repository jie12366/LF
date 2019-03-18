package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announce")
public class Announce extends BaseModel {

    @Column(name = "id",type = MySqlTypeConstant.INT, length = 10,isKey = true,isAutoIncrement = true)
    private int id;

    @Column(name = "content",type = MySqlTypeConstant.VARCHAR,length = 400)
    private String content;

    @Column(name = "date",type = MySqlTypeConstant.DATETIME)
    private Timestamp date;
}
