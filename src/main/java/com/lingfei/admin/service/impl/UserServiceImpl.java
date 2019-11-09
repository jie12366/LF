package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.User;
import com.lingfei.admin.mapper.UserMapper;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.utils.GetString;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:51
 */
@Service
@CacheConfig
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public User getUserId(int id) {
        return userMapper.getUserId(id);
    }

    @Override
    public int isExistsAccount(String account) {
        return userMapper.isExistsAccount(account);
    }

    @Override
    public int saveAccount(String account, String password) {
        if (account != null && password != null) {
            return userMapper.saveAccount(account, GetString.getMd5(password));
        }
        return 0;
    }

    @Override
    public boolean checkLogin(String account, String pass) {
        pass = GetString.getMd5(pass);
        String password = getUserByAccount(account).getPassword();

        if (pass != null){
            return pass.equals(password);
        }
        return false;
    }
}
