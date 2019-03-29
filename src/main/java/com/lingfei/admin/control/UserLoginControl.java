package com.lingfei.admin.control;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.utils.SimpleResponse;
import com.lingfei.admin.utils.VerifyUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/28 10:13
 */
@Controller
@RequestMapping("/user")
public class UserLoginControl {

    @Autowired
    UserServiceImpl userService;

    /**
     * 接受post方法，注册入口
     * @param account 账号
     * @param password 密码
     * @return 服务端跳转到login.html
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public String addUser(@ApiParam("用户名") @RequestParam String account, @ApiParam("密码") @RequestParam String password){
        userService.saveAccount(account,password);
        return "注册成功";
    }

    /**
     * 登录入口
     * @param account 用户名
     * @param password 密码
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public String adminLoginByPassword(@ApiParam("用户名") @RequestParam String account,
                                               @ApiParam("密码") @RequestParam String password, HttpServletRequest request) {
        if (VerifyUtil.checkVerifyCode(request)) {
            boolean user = userService.checkLogin(account,password);
            if (!user) {
                return "redirect:register";
            } else {
                return "redirect:index";
            }
        } else {
            return "redirect:register";
        }
    }

    @Autowired
    private Producer captchaProducer = null;

    @ApiOperation("生成验证码")
    @GetMapping("/getCode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
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
