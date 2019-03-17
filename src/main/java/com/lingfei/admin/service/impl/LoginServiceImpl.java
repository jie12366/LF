package com.lingfei.admin.service.impl;

import com.lingfei.admin.mapper.AdminMapper;
import com.lingfei.admin.entity.Admin;
import com.lingfei.admin.service.LoginService;
import com.lingfei.admin.utils.GetMd5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin returnResult(){
        return adminMapper.returnResult();
    }

    @Override
    public boolean adminLogin(Admin admin){
        String password = GetMd5.getMd5(admin.getPassword());
        if(admin.getUserName().equals(returnResult().getUserName()) && password.equals(returnResult().getPassword())){
            return  true;
        }
        return false;
    }
}
