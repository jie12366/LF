package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.service.impl.AnnounceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 熊义杰
 * @date 2019-3-17
 */

@Controller
public class AdminManageControl {

    @Autowired
    AnnounceServiceImpl announceService;

    /**
     * 接受get或者post方法
     * @param model Model
     * @param start 开始页码数
     * @param size 每页的大小
     * @return announce.html
     */
    @RequestMapping("/announce")
    public String getContent(Model model, @RequestParam(value = "start",defaultValue = "1") int start, @RequestParam(value = "size",defaultValue = "5") int size){
        PageHelper.startPage(start,size,"id asc");
        System.out.println(start);
        List<Announce> lists =  announceService.getAllResult();
        PageInfo<Announce> pageInfo = new PageInfo<>(lists);
        System.out.println(pageInfo.getList().get(0));
        model.addAttribute("pages",pageInfo);
        return "announce";
    }

    /**
     * 接受post方法，将表单传来的数据插入
     * @param announce com.lingfei.admin.entity.Announce
     * @return 服务端跳转到announce.html
     */
    @PostMapping("/addContent")
    public String addContent(Announce announce){
        announceService.save(announce);
        return "redirect:announce";
    }

    /**
     * 直接跳转
     * @return admin.html
     */
    @GetMapping("/admin")
    public String index(){
        return "admin";
    }
}
