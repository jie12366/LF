package com.lingfei.admin.control;

import com.lingfei.admin.service.impl.VisitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/6 13:38
 */

@Controller
public class FrontControl {

    @Autowired
    VisitorServiceImpl visitorService;

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
        if (visitorService.getVisitorByDate() == null) {
            visitorService.saveVisitor();
        } else {
            visitorService.updateVisitor();
        }
        return "front/about";
    }

    @GetMapping("/index/competition")
    public String toCompetition() {
        if (visitorService.getVisitorByDate() == null) {
            visitorService.saveVisitor();
        } else {
            visitorService.updateVisitor();
        }
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

    @GetMapping("/index/getPass")
    public String toGetPass(){
        return "front/getpass";
    }

    @GetMapping("/index/prefect")
    public String toPrefect(){
        return "front/prefect";
    }
}