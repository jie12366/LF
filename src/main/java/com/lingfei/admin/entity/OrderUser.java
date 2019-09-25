package com.lingfei.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/9/25 13:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUser implements Comparable<OrderUser> , Serializable {

    private static final long serialVersionUID = -5702855796239626149L;

    private int id;

    private User user;

    private Date time;

    @Override
    public int compareTo(OrderUser o) {
        // 先按优先级排序
        int i = this.user.getOrderCount() - o.user.getOrderCount();
        if (i == 0){
            // 再按约球时间排序
            return this.time.compareTo(o.time);
        }
        return i;
    }
}