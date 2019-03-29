package com.lingfei.admin;

import com.google.gson.Gson;
import com.lingfei.admin.service.UploadService;
import com.lingfei.admin.service.impl.UploadServiceImpl;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static junit.framework.TestCase.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(AdminApplicationTests.class);
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void get(){

        String key = "balabala";
        redisTemplate.opsForValue().set(key,"lalala");
        final String k2 = (String)redisTemplate.opsForValue().get(key);
        log.info("[自定义的字符缓存结果] - [{}]",k2);
    }

    @Autowired
    private UploadServiceImpl qiNiuService;
    @Test
    public void uploadFile() throws QiniuException {
        String fileName = "C:/Users/HP/Pictures/DailyBeautifulPic/22.jpg";
        File file = new File(fileName);
        assertTrue(file.exists());
        Response response = qiNiuService.uploadFile(file);
        assertTrue(response.isOK());
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        System.out.println(putRet.key);//根据 http://域名/key 就能访问文件
    }
}
