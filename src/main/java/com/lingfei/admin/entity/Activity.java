package com.lingfei.admin.entity;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 熊义杰
 * @date 2019-11-9
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activity")
public class Activity{

    @Column(name = "id", type = MySqlTypeConstant.INT, length = 10, isKey = true, isAutoIncrement = true)
    private int id;

    /**
     * 活动标题
     */
    @Column(name = "title", type = MySqlTypeConstant.VARCHAR)
    private String title;

    /**
     * 活动地点
     */
    @Column(name = "zone", type = MySqlTypeConstant.VARCHAR)
    private String zone;

    /**
     * 活动详情
     */
    @Column(name = "content", type = MySqlTypeConstant.VARCHAR)
    private String content;

    /**
     * 活动海报
     */
    @Column(name = "picture", type = MySqlTypeConstant.VARCHAR, length = 80)
    private String picture;

    /**
     * 活动时间
     */
    @Column(name = "date", type = MySqlTypeConstant.VARCHAR, length = 50)
    private String date;

    public Activity(String title, String zone, String content, String picture, String date) {
        this.title = title;
        this.zone = zone;
        this.content = content;
        this.picture = picture;
        this.date = date;
    }
}
