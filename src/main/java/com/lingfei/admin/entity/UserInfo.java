package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/6 22:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 6180694074767748836L;

    @Column(name = "id", type = MySqlTypeConstant.INT, length = 10, isKey = true, isAutoIncrement = true)
    private int id;

    /**
     * 用户id
     */
    @Column(name = "uid", type = MySqlTypeConstant.INT, length = 10)
    private int uid;

    /**
     * 第三方登录的uuid
     */
    @Column(name = "uuid", type = MySqlTypeConstant.VARCHAR, length = 40)
    private String uuid;

    /**
     * 用户头像路径
     */
    @Column(name = "headPath", type = MySqlTypeConstant.VARCHAR, length = 80)
    private String headPath;

    @Column(name = "name", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String name;

    @Column(name = "sex", type = MySqlTypeConstant.VARCHAR, length = 10)
    private String sex;

    @Column(name = "number", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String number;

    @Column(name = "stuClass", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String stuClass;

    @Column(name = "qq", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String qq;

    @Column(name = "email", type = MySqlTypeConstant.VARCHAR, length = 40)
    private String email;

    @Column(name = "depart", type = MySqlTypeConstant.VARCHAR, length = 20)
    private String depart;

    @Column(name = "orderCount", type = MySqlTypeConstant.INT, length = 10)
    private int orderCount;

    public UserInfo(int uid, String uuid, String headPath, int orderCount) {
        this.uid = uid;
        this.uuid = uuid;
        this.headPath = headPath;
        this.orderCount = orderCount;
    }

    public UserInfo(String uuid, String name, String sex, String number, String stuClass, String qq, String email, String depart) {
        this.uuid = uuid;
        this.name = name;
        this.sex = sex;
        this.number = number;
        this.stuClass = stuClass;
        this.qq = qq;
        this.email = email;
        this.depart = depart;
    }
}