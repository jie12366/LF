package com.lingfei.admin.control;

import com.lingfei.admin.entity.Admin;
import com.lingfei.admin.service.impl.LoginServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.service.impl.VisitorServiceImpl;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

/**
 * @author 熊义杰
 * @date 2019-3-16
 */

@Controller
@RequestMapping("/admin")
public class AdminLoginControl {

    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    VisitorServiceImpl visitorService;
    @Autowired
    UserServiceImpl userService;

    /**
     * 直接跳转
     * @return login.html
     */
    @GetMapping("/login")
    public String login(){
        return "adminLogin";
    }

    /**
     * 接受post方法，获取登录传来的用户信息，如果用户密码匹配就设置session
     * @param admin com.lingfei.admin.entity.Admin
     * @return 如果信息验证无误就跳转到admin.html，如果有误就服务端跳转login.html
     */
    @PostMapping("/")
    public String loginIn(Admin admin, HttpSession session){
        if(loginService.adminLogin(admin)){
            session.setAttribute("admin",admin.getUserName());
            if(visitorService.getVisitorByDate() == null) {
                visitorService.saveVisitor();
            }else {
                visitorService.updateVisitor();
            }
            return "redirect:index";
        }
        return "redirect:login";
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("admin");
        return "adminLogin";
    }

    @GetMapping("/form")
    public String forms(){
        return "announce/forms";
    }

    /**
     * 转到三个表
     * @return 表的路径
     */

    @GetMapping("/table3")
    public String table3(){
        return "table3/table3";
    }
}
