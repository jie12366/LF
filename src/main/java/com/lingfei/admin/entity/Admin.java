package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.command.BaseModel;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin")
public class Admin extends BaseModel {

    @Column(name = "userName",type = MySqlTypeConstant.VARCHAR,length = 10)
    private String userName;

    @Column(name = "password",type = MySqlTypeConstant.VARCHAR,length = 40)
    private String password;
}
