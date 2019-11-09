package com.lingfei.admin.control;

import com.lingfei.admin.annotation.LoginToken;
import com.lingfei.admin.entity.UserInfo;
import com.lingfei.admin.service.UploadService;
import com.lingfei.admin.service.UserInfoService;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/8 18:00
 */
@RestController
public class UserInfoController {

    @Resource
    UserInfoService userInfoService;

    @Resource
    UploadService uploadService;

    private static final Logger log = LoggerFactory.getLogger(UserInfoController.class);

    @LoginToken
    @ApiOperation("获取个人信息")
    @GetMapping("/userInfo/{uid}")
    public JsonResult getInfo(@PathVariable("uid") String uid){
        UserInfo userInfo = userInfoService.getUser(uid);
        if (userInfo != null){
            return JsonResult.success(userInfo);
        }else {
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }
    }

    @LoginToken
    @ApiOperation("更换头像")
    @PostMapping("/headPath")
    public JsonResult updateHeadPath(@RequestParam("image")MultipartFile image,
                                     @RequestParam("uid") String uid, HttpServletRequest request){
        String path = uploadService.getPath(image,request);

        // 如果返回路径为空，说明文件上传失败
        if (path == null){
            return JsonResult.failure(ResultCode.FILE_UPLOAD_ERROR);
        }

        // 更新头像
        int res = userInfoService.updateHeadPath(path,uid);
        if (res == 1){
            return JsonResult.success(path);
        }else {
            return JsonResult.failure(ResultCode.UPDATE_ERROR);
        }
    }

    @LoginToken
    @ApiOperation("更新个人信息")
    @PutMapping("/userInfo")
    public JsonResult updateInfo(@RequestParam("uid")String uid, @RequestParam("sex") String sex,
                                 @RequestParam("number")String number, @RequestParam("stuClass") String stuClass,
                                 @RequestParam("qq")String qq, @RequestParam("depart") String depart,
                                 @RequestParam("email")String email,@RequestParam("name")String name){
        UserInfo userInfo = new UserInfo(uid,name,sex,number,stuClass,qq,email,depart);
        int res = userInfoService.updateUser(userInfo);
        if (res == 0){
            return JsonResult.failure(ResultCode.UPDATE_ERROR);
        }else {
            return JsonResult.success();
        }
    }
}