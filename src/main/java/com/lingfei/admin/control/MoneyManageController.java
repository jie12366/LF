package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.MoneyManage;
import com.lingfei.admin.service.MoneyManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/5/31 12:14
 */
@Controller
@RequestMapping("/moneyManage")
public class MoneyManageController {

    @Autowired
    MoneyManageService manageService;

    @GetMapping("/show")
    public String show(Model model,@RequestParam(value = "start",defaultValue = "1") int start,
                       @RequestParam(value = "size",defaultValue = "5") int size){

        PageHelper.startPage(start,size,"id desc");
        List<MoneyManage> manageList = manageService.listManage();
        PageInfo<MoneyManage> pageInfo = new PageInfo<>(manageList);
        model.addAttribute("pages",pageInfo);
        return "money/show";
    }

    @GetMapping("/add")
    public String toAdd(){
        return "money/add";
    }

    @PostMapping("/add")
    public String add(MoneyManage manage){
        manageService.saveMoneyManage(manage);
        return "redirect:show";
    }

    @GetMapping("/edit")
    public String toEdit(Model model,int id){
        MoneyManage manage = manageService.getManageById(id);
        if (manage != null){
            model.addAttribute("moneyMange",manage);
            return "money/edit";
        }else {
            return "redirect:show";
        }
    }

    @PostMapping("/edit")
    public String edit(MoneyManage manage){
        manageService.updateManage(manage);
        return "redirect:show";
    }

    @GetMapping("/delete")
    public String delete(int id){
        manageService.deleteManage(id);
        return "redirect:show";
    }
}