package com.lingfei.admin.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "announce")
public class Announce extends BaseModel {

    @Excel(name = "序号", orderNum = "0")
    @Column(name = "id",type = MySqlTypeConstant.INT, length = 10,isKey = true,isAutoIncrement = true)
    private int id;

    @Excel(name = "内容", orderNum = "1")
    @Column(name = "content",type = MySqlTypeConstant.VARCHAR,length = 400)
    private String content;

    @Excel(name = "时间", exportFormat = "yyyy-MM-dd hh:mm" ,orderNum = "2")
    @Column(name = "date",type = MySqlTypeConstant.DATETIME)
    private Timestamp date;
}
