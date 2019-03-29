package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.User;
import com.lingfei.admin.mapper.UserMapper;
import com.lingfei.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 21:51
 */
@Service
@CacheConfig
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUser(){
        return userMapper.listUser();
    }

    @Override
    @Cacheable(cacheNames = "user")
    public User getUser(int id){
        if(id > 0){
            return userMapper.getUser(id);
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "user")
    public String getPassword(String account){
        if(account != null){
            userMapper.getPassword(account);
        }
        return null;
    }

    @Override
    public int saveAccount(String account,String password){
        if (account != null && password != null){
            return userMapper.saveAccount(account,password);
        }
        return 0;
    }

    @Override
    @CachePut(cacheNames = "user")
    public int updateUser(User user){
        return userMapper.updateUser(user.getName(),user.getNumber(),user.getStuClass(),user.getQq(),user.getEmail(),user.getPhone(),user.getDepart(),user.getId());
    }

    @Override
    @CachePut(cacheNames = "user")
    public int updateBalance(User user){
        if(user != null){
            return userMapper.updateBalance(user.getBalance(),user.getId());
        }
        return 0;
    }

    @Override
    @CachePut(cacheNames = "user")
    public int updateDynamicUser(User user){
        if(user != null){
            return userMapper.updateDynamicUser(user);
        }
        return 0;
    }

    @Override
    @CacheEvict(cacheNames = "user")
    public int deleteUser(int id){
        if(id > 0){
            return userMapper.deleteUser(id);
        }
        return 0;
    }

    @Override
    @CacheEvict(cacheNames = "user")
    public int batchDelete(List<User> users){
        if(users != null){
            return userMapper.batchDeleteUser(users);
        }
        return 0;
    }

    @Override
    public boolean checkLogin(String account,String pass){
        String  password = this.getPassword(account);
        if (pass.equals(password)){
            return true;
        }
        return false;
    }
}
