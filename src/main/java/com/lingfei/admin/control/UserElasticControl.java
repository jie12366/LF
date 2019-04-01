package com.lingfei.admin.control;

import com.lingfei.admin.entity.Notice;
import com.lingfei.admin.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/30 21:12
 */
@Controller
@RequestMapping("es")
public class UserElasticControl {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/user")
    public String  getUserById(String name, Model model){
        Page<Notice> page = noticeService.findByName(name,PageRequest.of(0,10));
        model.addAttribute("pages",page.getContent());
        return "table1/esTable1";
    }
}
