package com.lingfei.admin.service.impl;

import com.lingfei.admin.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/31 23:10
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 邮件发件人
     */
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    TemplateEngine templateEngine;

    @Override
    public int sendEmail(String to, String object, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(object);
        message.setText(content);
        try {
            mailSender.send(message);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
