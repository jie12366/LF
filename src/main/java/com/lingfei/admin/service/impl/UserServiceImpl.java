package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.User;
import com.lingfei.admin.mapper.UserMapper;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.utils.GetString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    public List<User> listUser() {
        return userMapper.listUser();
    }

    @Override
    public User getUser(String id) {
        if (id != null) {
            return userMapper.getUser(id);
        }
        return null;
    }

    @Override
    public List<User> getUserByName(String name) {
        if (name != null){
            return userMapper.getUserByName(name);
        }
        return null;
    }

    @Override
    public String getId(String account) {
        return userMapper.getId(account);
    }

    @Override
    public String getPasswordByAccount(String account) {
        if (account != null) {
            return userMapper.getPasswordByAccount(account);
        }
        return null;
    }

    @Override
    public String getPasswordByEmail(String email) {
        if (email != null) {
            return userMapper.getPasswordByEmail(email);
        }
        return null;
    }

    @Override
    public int isExistsAccount(String account) {
        return userMapper.isExistsAccount(account);
    }

    @Override
    public int isExistsUuid(String uuid) {
        return userMapper.isExistsUuid(uuid);
    }

    @Override
    public int saveAccount(String account, String password) {
        if (account != null && password != null) {
            System.out.println(password);
            return userMapper.saveAccount(account, GetString.getMd5(password));
        }
        return 0;
    }

    @Override
    public int resetPassword(String password, String phone) {
        if (phone != null) {
            return userMapper.resetPassword(password, phone);
        }
        return 0;
    }

    @Override
    @CachePut(cacheNames = "user")
    public int updateUser(User user) {
        return userMapper.updateUser(user.getName(), user.getNumber(), user.getStuClass(), user.getQq(), user.getEmail(), user.getDepart(), user.getUuid());
    }

    @Override
    public int updateUuid(String account, String uuid) {
        return userMapper.updateUuid(account, uuid);
    }

    @Override
    public int updateCount(int count,String uid) {
        if (count != 0) {
            return userMapper.updateCount(count,uid);
        }
        return 0;
    }

    @Override
    @CachePut(cacheNames = "user")
    public int updateDynamicUser(User user) {
        if (user != null) {
            return userMapper.updateDynamicUser(user);
        }
        return 0;
    }

    @Override
    @CacheEvict(cacheNames = "user")
    public int deleteUser(String id) {
        if (id != null) {
            return userMapper.deleteUser(id);
        }
        return 0;
    }

    @Override
    public boolean checkLogin(String account, String pass) {
        System.out.println(account+pass);
        pass = GetString.getMd5(pass);
        String password = getPasswordByAccount(account);

        if (password == null) {
            password = this.getPasswordByEmail(account);
        }
        System.out.println(password + " "+pass);
        if (pass.equals(password)) {
            return true;
        }
        return false;
    }
}
