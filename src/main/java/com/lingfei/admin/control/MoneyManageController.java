package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.MoneyManage;
import com.lingfei.admin.service.MoneyManageService;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("分页展示财务管理信息")
    @GetMapping("/show")
    public String show(Model model,@RequestParam(value = "start",defaultValue = "1") int start,
                       @RequestParam(value = "size",defaultValue = "5") int size){

        PageHelper.startPage(start,size,"id desc");
        List<MoneyManage> manageList = manageService.listManage();
        PageInfo<MoneyManage> pageInfo = new PageInfo<>(manageList);
        model.addAttribute("pages",pageInfo);
        return "money/show";
    }

    @ApiOperation("跳转到添加界面")
    @GetMapping("/add")
    public String toAdd(){
        return "money/add";
    }

    @ApiOperation("将信息添加到数据库")
    @PostMapping("/add")
    public String add(MoneyManage manage){
        manageService.saveMoneyManage(manage);
        return "redirect:show";
    }

    @ApiOperation("根据id获取数据，并将数据传到编辑页面")
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

    @ApiOperation("编辑完数据后，提交到这进行数据库的更新")
    @PostMapping("/edit")
    public String edit(MoneyManage manage){
        manageService.updateManage(manage);
        return "redirect:show";
    }

    @ApiOperation("删除这个id对应的信息")
    @GetMapping("/delete")
    public String delete(int id){
        manageService.deleteManage(id);
        return "redirect:show";
    }
}