package com.lingfei.admin.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 15:44
 */
public interface UploadService {
    /**
     * 上传文件
     *
     * @param file File
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file) throws QiniuException;

    /**
     * 根据文件名删除空间中的文件
     *
     * @param key 文件名
     */
    void deleteFile(String key) throws QiniuException;
}
