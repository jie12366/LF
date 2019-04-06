package com.lingfei.admin.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/4/6 13:38
 */

@Controller
public class FrontControl {

    /**
     * 直接跳转到主界面
     * @return
     */
    @GetMapping("")
    public String toIndex(){
        return "front/index";
    }
    @GetMapping("/")
    public String index(){
        return "front/index";
    }

    @GetMapping("/index/about")
    public String toAbout(){
        return "front/about";
    }

    @GetMapping("/index/competition")
    public String toCompetition(){
        return "front/competition";
    }
}