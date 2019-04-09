package com.lingfei.admin.control;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lingfei.admin.entity.Notice;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.impl.EmailServiceImpl;
import com.lingfei.admin.service.impl.NoticeServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.utils.GetString;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.VerifyUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    NoticeServiceImpl noticeService;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    private RedisTemplate<String, String> template;

    /**
     * 接受post方法，注册入口
     *
     * @param account  账号
     * @param password 密码
     * @return 服务端跳转到login.html
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public JsonResult addUser(@ApiParam("账号") @RequestParam String account, @ApiParam("密码") @RequestParam String password,
                              @ApiParam("验证码") String code) {
        List<User> lists = userService.listUser();
        for (User user : lists) {
            if (user.getAccount().equals(account)) {
                return JsonResult.errorMsg("账号已经被注册");
            }
        }
        String code1 = template.opsForValue().get("code");
        if (code1 == null) {
            return JsonResult.errorMsg("验证码已过期");
        }
        if (!code1.equals(code)) {
            return JsonResult.errorMsg("验证码错误");
        }
        userService.saveAccount(account, password);
        int id = userService.getId(account);
        Notice notice = new Notice(id + "", account, null, null, null, null, null, null, null, 0);
        noticeService.save(notice);
        return JsonResult.ok(notice);
    }

    @ApiOperation("生成注册验证码")
    @GetMapping("/getRegisterCode")
    public JsonResult getRegisterCode(@ApiParam("验证码接收者") String to) {
        String code = GetString.getCode();
        String email = "您本次注册的验证码为： " + code;
        int res = emailService.sendEmail(to, "注册验证码", email);
        if (res == 1) {
            //用redis存邮件发送的验证码,设置5分钟过期
            template.opsForValue().set("code", code);
            template.expire("code", 3000, TimeUnit.SECONDS);
            return JsonResult.ok();
        } else {
            return JsonResult.errorMsg("验证码发送错误，请重试");
        }
    }

    @ApiOperation("重置密码邮件")
    @GetMapping("/reset")
    public JsonResult reset(@ApiParam("重置密码接收者") String to) {
        String email = "请点击以下链接来重置你的密码，如非本人操作，请忽视。\n"
                + " http://127.0.0.1:88/user/resetPass";
        int res = emailService.sendEmail(to, "重置密码", email);
        if (res == 1) {
            return JsonResult.ok();
        } else {
            return JsonResult.errorMsg("邮件发送失败");
        }
    }

    @ApiOperation("重置密码界面")
    @GetMapping("resetPass")
    public String resetPass() {
        return "resetPass";
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public JsonResult resetPassword(@ApiParam("邮箱") String email, @ApiParam("新密码") String password) {
        int res = userService.resetPassword(password, email);
        if (res == 1) {
            return JsonResult.ok();
        } else {
            return JsonResult.errorMsg("邮箱错误，请重新填写");
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
    public JsonResult adminLoginByPassword(@ApiParam("账号") @RequestParam String account,
                                           @ApiParam("密码") @RequestParam String password, HttpServletRequest request) {
        if (VerifyUtil.checkVerifyCode(request)) {
            boolean user = userService.checkLogin(account, password);
            if (!user) {
                return JsonResult.errorMsg("账号或者密码错误");
            } else {
                return JsonResult.ok(user);
            }
        } else {
            return JsonResult.errorMsg("验证码错误");
        }
    }

    @Autowired
    private Producer captchaProducer = null;

    @ApiOperation("生成登录验证码")
    @GetMapping("/getLoginCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
