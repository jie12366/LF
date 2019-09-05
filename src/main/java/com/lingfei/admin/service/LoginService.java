package com.lingfei.admin.service;

import com.lingfei.admin.entity.Admin;
import org.springframework.stereotype.Service;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

public interface LoginService {
    /**
     * 判断是否是管理员用户
     *
     * @param admin com.lingfei.admin.entity.admin
     * @return true or false
     */
    boolean adminLogin(Admin admin);

    /**
     * 返回结果集
     *
     * @return com.lingfei.admin.entity.admin
     */
    Admin returnResult();
}
