package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.AnnounceService;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.service.impl.VisitorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/6 13:38
 */

@Controller
@Api("前台页面的跳转")
public class FrontControl {

    @Autowired
    VisitorServiceImpl visitorService;
    @Autowired
    AnnounceService announceService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        if (visitorService.getVisitorByDate() == null) {
            visitorService.saveVisitor();
        } else {
            visitorService.updateVisitor();
        }
        return "front/index";
    }

    @GetMapping("/index/about")
    public String toAbout() {
        return "front/about";
    }

    @GetMapping("/index/competition")
    public String toCompetition() {
        return "front/competition";
    }

    @GetMapping("/index/login")
    public String toLogin(){
        return "front/login";
    }

    @GetMapping("/index/register")
    public String toRegister(){
        return "front/reg";
    }

    @GetMapping("/index/rules")
    public String toRules(){
        return "front/rules";
    }

    @GetMapping("/index/getPass")
    public String toGetPass(){
        return "front/getpass";
    }

    @GetMapping("/index/prefect")
    public String toPrefect(){
        return "front/prefect";
    }

    @ApiOperation("公告信息分页展示")
    @GetMapping("/index/announce")
    public String toAnnounce(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "id desc");
        List<Announce> lists = announceService.getAllResult();
        PageInfo<Announce> pageInfo = new PageInfo<>(lists);
        model.addAttribute("announces", pageInfo);
        return "front/announce";
    }

    @ApiOperation("个人信息展示")
    @GetMapping("/index/message")
    public String toMessage(Model model, HttpServletRequest request){
        String user1 = (String)request.getSession().getAttribute("user");
        User user = userService.getUser(userService.getId(user1));
        model.addAttribute("user", user);
        return "front/message";
    }

    @ApiOperation("个人信息的修改")
    @PostMapping("/front/user/edit")
    public String updateUser(User user) {
        userService.updateDynamicUser(user);
        return "front/message";
    }
}