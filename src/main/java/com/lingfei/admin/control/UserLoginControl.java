package com.lingfei.admin.control;

import com.lingfei.admin.entity.ImageCode;
import com.lingfei.admin.service.impl.EmailServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.utils.CreateImageCode;
import com.lingfei.admin.utils.GetString;
import com.lingfei.admin.utils.JsonResult;
import com.zhenzi.sms.ZhenziSmsClient;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 10:13
 */
@RestController
@RequestMapping("/user")
public class UserLoginControl {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    EmailServiceImpl emailService;

    @ApiOperation("检查短信验证码是否正确")
    @PostMapping("/checkSmsCode")
    public JsonResult checkSmsCode(@ApiParam("验证码") @RequestParam("code") String code,
                                   HttpServletRequest request){

        JSONObject imageCode =  (JSONObject) request.getServletContext().getAttribute("smsCode");
        if (imageCode == null){
            return JsonResult.errorMsg("1");
        }else if (!StringUtils.equalsIgnoreCase((String) imageCode.get("code"),code)){
            return JsonResult.errorMsg("2");
        }else {
            request.getServletContext().removeAttribute("smsCode");
            return JsonResult.ok();
        }
    }

    @ApiOperation("检查账号是否已经注册")
    @GetMapping("/check/register")
    public JsonResult checkRegister(@ApiParam("账号") String account){
        if (userService.isExistsAccount(account) == 1){
            return JsonResult.errorMsg("已存在");
        }
        return JsonResult.ok();
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
    public JsonResult addUser(@ApiParam("账号") @RequestParam("account") String account,
                              @ApiParam("密码") @RequestParam("password") String password) {

        userService.saveAccount(account, password);
        return JsonResult.ok();
    }

    @ApiOperation("短信验证码")
    @GetMapping("/sendMsg")
    public JsonResult sendMsg(HttpServletRequest request, @RequestParam("phone") String phone) throws Exception{
        System.out.println(phone);
        //榛子短信的SDK
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com", "101348", "ZGZmNjM3MWYtZDVjMS00YWUyLWE4NmUtZDI5NjNmOGRjNTA1");
        String code = GetString.getCode();
        String result = client.send(phone, "您的验证码为" + code + "\n" + "如果不是本人操作，请忽略。");
        JSONObject jsonObject = JSONObject.fromObject(result);
        if (jsonObject.getInt("code") != 0){
            return JsonResult.errorMsg("验证码发送失败");
        }
        //设置验证码5分钟后过期
        JSONObject smsCode = new JSONObject();
        smsCode.put("code",code);
        smsCode.put("time",System.currentTimeMillis());
        System.out.println(code);

        request.getServletContext().setAttribute("smsCode",smsCode);

        return JsonResult.ok();
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public JsonResult resetPassword(@ApiParam("手机号")@RequestParam("phone") String phone,
                                    @ApiParam("新密码")@RequestParam("password") String password) {

        int res = userService.resetPassword(password, phone);
        if (res == 1) {
            return JsonResult.ok();
        } else {
            return JsonResult.errorMsg("1");
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
                                           @ApiParam("密码") @RequestParam("password") String password,
                                           @ApiParam("密码") @RequestParam("code") String code,
                                           HttpServletRequest request) {

        ImageCode imageCode =  (ImageCode) request.getServletContext().getAttribute("code");
        if (imageCode.isExpired()){
            return JsonResult.errorMsg("1");
        }else if (!StringUtils.equalsIgnoreCase(imageCode.getCode(),code)){
            return JsonResult.errorMsg("2");
        }else if (!userService.checkLogin(account,password)){
            return JsonResult.errorMsg("3");
        }else {
            request.getSession().setAttribute("user",account);
            return JsonResult.ok(userService.getId(account));
        }
    }

    @ApiOperation("图形验证码")
    @GetMapping(value = "/getCode",produces = "image/jpeg")
    public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException {

        ImageCode imageCode = CreateImageCode.createImagecode();
        request.getServletContext().setAttribute("code",imageCode);

        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }
}
