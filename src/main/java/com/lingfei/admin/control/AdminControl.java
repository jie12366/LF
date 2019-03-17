package com.lingfei.admin.control;

import com.lingfei.admin.entity.Admin;
import com.lingfei.admin.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Controller
public class AdminControl {

    @Autowired
    LoginServiceImpl loginService;

    /**
     * 直接跳转
     * @return login.html
     */
    @GetMapping("/login")
    public String login(){
        return "adminLogin";
    }

    /**
     * 接受post方法，获取登录传来的用户信息
     * @param admin com.lingfei.admin.entity.Admin
     * @return 如果信息验证无误就跳转到admin.html，如果有误就服务端跳转login.html
     */
    @PostMapping("/admin")
    public String loginIn(Admin admin){
        if(loginService.adminLogin(admin)){
            return "admin";
        }
        return "redirect:login";
    }

    @GetMapping("/form")
    public String forms(){
        return "forms";
    }

    /**
     * 转到三个表
     * @return 表的路径
     */
    @GetMapping("/table1")
    public String table1(){
        return "table1";
    }

    @GetMapping("/table2")
    public String table2(){
        return "table2";
    }

    @GetMapping("/table3")
    public String table3(){
        return "table3";
    }
}
