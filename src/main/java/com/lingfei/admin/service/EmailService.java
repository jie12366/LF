package com.lingfei.admin.service;

import org.springframework.stereotype.Service;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 23:07
 */
@Service
public interface EmailService {

    /**
     * 邮件发送
     *
     * @param to      接受者
     * @param object  主题
     * @param content 验证码
     */
    int sendEmail(String to, String object, String content);
}
