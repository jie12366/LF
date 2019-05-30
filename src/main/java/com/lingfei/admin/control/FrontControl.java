package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.service.AnnounceService;
import com.lingfei.admin.service.impl.VisitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/6 13:38
 */

@Controller
public class FrontControl {

    @Autowired
    VisitorServiceImpl visitorService;
    @Autowired
    AnnounceService announceService;

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

    @GetMapping("/index/announce")
    public String toAnnounce(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start, size, "id desc");
        List<Announce> lists = announceService.getAllResult();
        PageInfo<Announce> pageInfo = new PageInfo<>(lists);
        model.addAttribute("announces", pageInfo);
        return "front/announce";
    }
}