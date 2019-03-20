package com.lingfei.admin.control;

import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.impl.AnnounceServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/18 18:25
 */

@Controller
public class AdminExcelControl {

    @Autowired
    AnnounceServiceImpl announceService;

    @GetMapping("/getExcel1")
    public void export1(HttpServletResponse response){
        List<Announce> announces = announceService.getAllResult();
        //导出到Excel
        ExcelUtils.exportExcel(announces,"公告记录","jie",Announce.class,"测试.xls",response);
    }

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/getExcel2")
    public void export2(HttpServletResponse response){
        List<User> users = userService.listUser();
        //导出到Excel
        ExcelUtils.exportExcel(users,"成员信息表","jie",User.class,"测试.xls",response);
    }

    /*@GetMapping("/getExcel3")
    public void export3(HttpServletResponse response){
        List<Announce> announces = announceService.getAllResult();
        //导出到Excel
        ExcelUtils.exportExcel(announces,"公告记录","jie",Announce.class,"测试.xls",response);
    }

    @GetMapping("/getExcel4")
    public void export4(HttpServletResponse response){
        List<Announce> announces = announceService.getAllResult();
        //导出到Excel
        ExcelUtils.exportExcel(announces,"公告记录","jie",Announce.class,"测试.xls",response);
    }*/
}
