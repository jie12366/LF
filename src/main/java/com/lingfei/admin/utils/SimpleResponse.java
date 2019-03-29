package com.lingfei.admin.utils;

import lombok.Data;

/**
 * 返回类型的工具类
 * @author www.xyjz123.xyz
 * @date 2019/3/28 10:19
 */
@Data
public class SimpleResponse {

    private Object content;
    public SimpleResponse(Object content){
        this.content = content;
    }
}
