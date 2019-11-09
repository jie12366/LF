package com.lingfei.admin.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lingfei.admin.entity.User;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/6 20:15
 */
public class JwtUtil {

    /**
     * 生成token，将用户id存入数据
     * 设置过期时间为10分钟
     * @param users Users
     * @return 生成的token字符串
     */
    public static String generateToken(User users){
        //MD5加密的密码和当前时间戳作为数字签证
        String secret = GetString.getMd5(users.getPassword()) + System.currentTimeMillis();
        return JWT.create()
                .withAudience(String.valueOf(users.getId()))
                .sign(Algorithm.HMAC256(secret));
    }
}