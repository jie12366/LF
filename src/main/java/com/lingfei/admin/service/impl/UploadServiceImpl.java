package com.lingfei.admin.service.impl;

import com.lingfei.admin.service.UploadService;
import com.lingfei.admin.utils.QiniuUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 15:46
 */
@Service
public class UploadServiceImpl implements UploadService, InitializingBean {
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private Auth auth;
    @Autowired
    BucketManager bucketManager;
    @Autowired
    private QiniuUtil qiNiuProperties;
    private StringMap putPolicy;
    String key = null;

    @Override
    public Response uploadFile(File file) throws QiniuException {
        Response response = this.uploadManager.put(file, key, getUploadToken());
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, key, getUploadToken());
            retry++;
        }
        return response;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuProperties.getBucket(), null, 3600, putPolicy);
    }

    @Override
    public void deleteFile(String key) throws QiniuException {
        bucketManager.delete(qiNiuProperties.getBucket(), key);
    }
}
