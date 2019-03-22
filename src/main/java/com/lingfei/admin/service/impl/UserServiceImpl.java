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
    @Cacheable(cacheNames = "user")
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
    public int saveUser(User user){
        if(user != null){
            return userMapper.saveUser(user.getName(),user.getNumber(),user.getStuClass(),user.getQq(),user.getEmail(),user.getPhone(),user.getDepart());
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
}
