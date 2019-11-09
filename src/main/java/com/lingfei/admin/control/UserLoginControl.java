package com.lingfei.admin.control;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.entity.UserInfo;
import com.lingfei.admin.service.UserInfoService;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.utils.GetString;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.JwtUtil;
import com.lingfei.admin.utils.ResultCode;
import com.zhenzi.sms.ZhenziSmsClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 10:13
 */
@RestController
public class UserLoginControl {

    @Resource
    UserService userService;
    @Resource
    UserInfoService userInfoService;

    @Resource
    ValueOperations<String ,String> valueOperations;

    private final static String SMS = "smsCaptcha";

    @ApiOperation("发送短信验证码")
    @PostMapping("/phone")
    public JsonResult sendMsg(@RequestParam("phone") String phone) throws Exception{
        System.out.println(phone);
        String code = GetString.getCode();
        System.out.println("code = " + code);
        //榛子短信的SDK
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101348", "ZGZmNjM3MWYtZDVjMS00YWUyLWE4NmUtZDI5NjNmOGRjNTA1");
        String result = client.send(phone, "您的验证码为" + code + "\n" + "如果不是本人操作，请忽略。");
        com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(result);

        if ((Integer) jsonObject.get("code") != 0){
            return JsonResult.failure(ResultCode.CAPTCHA_IS_ERROR);
        }

        // 设置验证码1分钟后过期
        valueOperations.set(SMS,code,1, TimeUnit.MINUTES);

        return JsonResult.success();
    }

    /**
     * 接受post方法，注册入口
     *
     * @param account  账号
     * @param password 密码
     * @return 服务端跳转到login.html
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public JsonResult addUser(@Valid @RequestParam("code") String code,
                              @Valid @ApiParam("账号") @RequestParam("account") String account,
                              @Valid @ApiParam("密码") @RequestParam("password") String password) {

        // 检查验证码是否正确
        String verifyCode = valueOperations.get(SMS);
        if (verifyCode == null){
            return JsonResult.failure(ResultCode.CAPTCHA_HAS_EXPIRED);
        }
        if (!StringUtils.equalsIgnoreCase(code,verifyCode)){
            return JsonResult.failure(ResultCode.CAPTCHA_IS_ERROR);
        }

        User user = userService.getUserByAccount(account);
        if (user != null){
            return JsonResult.failure(ResultCode.USER_HAS_EXISTED);
        }

        // 存入账号信息，并初始化个人信息
        int res = userService.saveAccount(account, password);
        int uid = userService.getUserByAccount(account).getId();
        UserInfo userInfo = new UserInfo(uid,String.valueOf(uid),"http://cdn.jie12366.xyz/face.jpg",0);
        int res1 = userInfoService.save(userInfo);
        if (res1 == 0 || res == 0){
            return JsonResult.failure(ResultCode.SAVE_ERROR);
        }else {
            return JsonResult.success();
        }
    }

    /**
     * 登录入口
     *
     * @param account  账号
     * @param password 密码
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public JsonResult adminLoginByPassword(@ApiParam("账号") @RequestParam("account") String account,
                                           @ApiParam("密码") @RequestParam("password") String password) {

        // 检查用户是否存在
        if (userService.isExistsAccount(account) == 0){
            return JsonResult.failure(ResultCode.USER_NOT_EXIST);
        }

        // 检查账号密码是否正确
        if (!userService.checkLogin(account,password)){
            return JsonResult.failure(ResultCode.USER_LOGIN_ERROR);
        }else {
            User users1 = userService.getUserByAccount(account);
            //利用JWT生成token
            String token = JwtUtil.generateToken(users1);
            //将生成的token的签证作为redis的键
            String key = token.split("\\.")[2];
            //将token存入redis并设置过期时间为7小时
            valueOperations.set(key,token,7, TimeUnit.DAYS);

            JSONObject json = JSONUtil.createObj();
            json.put("token", token);
            json.put("uid",userInfoService.getUuid(users1.getId()));
            return JsonResult.success(json);
        }
    }
}
