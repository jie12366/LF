package com.lingfei.admin.control;

import com.lingfei.admin.entity.Admin;
import com.lingfei.admin.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Controller
public class AdminLoginControl {

    @Autowired
    LoginServiceImpl loginService;

    /**
     * 直接跳转
     * @return login.html
     */
    @GetMapping("/adminLogin")
    public String login(){
        return "adminLogin";
    }

    /**
     * 接受post方法，获取登录传来的用户信息，如果用户密码匹配就设置session
     * @param admin com.lingfei.admin.entity.Admin
     * @return 如果信息验证无误就跳转到admin.html，如果有误就服务端跳转login.html
     */
    @RequestMapping("/")
    public String loginIn(Admin admin, HttpSession session){
        if(loginService.adminLogin(admin)){
            session.setAttribute("user",admin.getUserName());
            return "admin";
        }
        return "redirect:adminLogin";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "/adminLogin";
    }

    @GetMapping("/form")
    public String forms(){
        return "announce/forms";
    }

    /**
     * 转到三个表
     * @return 表的路径
     */

    @GetMapping("/table2")
    public String table2(){
        return "table2/table2";
    }

    @GetMapping("/table3")
    public String table3(){
        return "table3/table3";
    }
}
