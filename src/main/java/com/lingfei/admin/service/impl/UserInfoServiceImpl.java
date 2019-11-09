package com.lingfei.admin.service.impl;

import com.lingfei.admin.entity.UserInfo;
import com.lingfei.admin.mapper.UserInfoMapper;
import com.lingfei.admin.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/7 9:44
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Override
    public List<UserInfo> listUser() {
        return userInfoMapper.listUser();
    }

    @Override
    public List<UserInfo> getUserByName(String name) {
        return userInfoMapper.getUserByName(name);
    }

    @Override
    public UserInfo getUser(String uuid) {
        return userInfoMapper.getUser(uuid);
    }

    @Override
    public int isExistsUuid(String uuid) {
        return userInfoMapper.isExistsUuid(uuid);
    }

    @Override
    public int updateUuid(int uid, String uuid) {
        return userInfoMapper.updateUuid(uid, uuid);
    }

    @Override
    public String getUuid(int uid) {
        return userInfoMapper.getUuid(uid);
    }

    @Override
    public int save(UserInfo userInfo) {
        return userInfoMapper.save(userInfo.getUid(),userInfo.getUuid(),userInfo.getHeadPath(),userInfo.getOrderCount());
    }

    @Override
    public int updateUser(UserInfo userInfo) {
        return userInfoMapper.updateUser(userInfo.getName(),userInfo.getNumber(),userInfo.getSex(),
                userInfo.getStuClass(),userInfo.getQq(),userInfo.getEmail(),userInfo.getDepart(),userInfo.getUuid());
    }

    @Override
    public int updateHeadPath(String headPath, String uuid) {
        return userInfoMapper.updateHeadPath(headPath, uuid);
    }

    @Override
    public int updateCount(int count, String uuid) {
        return userInfoMapper.updateCount(count, uuid);
    }

    @Override
    public int deleteUser(String uuid) {
        return userInfoMapper.deleteUser(uuid);
    }
}