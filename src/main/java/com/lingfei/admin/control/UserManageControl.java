package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 22:15
 */

@Controller
public class UserManageControl {

    @Autowired
    UserServiceImpl userService;

    /**
     * 获取所有user数据，并分页
     * @param model Model
     * @param start 开始页
     * @param size 每页的大小
     * @return table1.html
     */
    @RequestMapping("/table1")
    public String getTable1(Model model, @RequestParam(value = "start",defaultValue = "1") int start, @RequestParam(value = "size",defaultValue = "5") int size){
        PageHelper.startPage(start,size,"id asc");
        List<User> lists =  userService.listUser();
        PageInfo<User> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pages",pageInfo);
        return "table1/table1";
    }

    /**
     * 根据传来的id更改对应的数据
     * @param id int
     * @param model Model
     * @return editUser.html
     */
    @GetMapping("/editUser")
    public String editUser(int id,Model model){
        User user =  userService.getUser(id);
        if(user != null){
            model.addAttribute("user",user);
            return "table1/editUser";
        }
        return "redirect:table1";
    }

    /**
     * 编辑完数据后，提交到这进行数据库的更新
     * @param user User
     * @return 返回table1表
     */
    @PostMapping("/updateUser")
    public String updateUser(User user){
        userService.updateDynamicUser(user);
        return "redirect:table1";
    }

    /**
     * 根据get传来的id删除对应的数据
     * @param id inr
     * @return 返回table1表
     */
    @GetMapping("/deleteUser")
    public String deleteUser(String id){
        if(!id.matches(",")){
            int i = Integer.parseInt(id);
            userService.deleteUser(i);
        }else {
            String[] id3 = id.split(",");
            List<User> users = new ArrayList<>();
            for (String id1:id3){
                int id2 = Integer.parseInt(id1);
                users.add(userService.getUser(id2));
            }
            userService.batchDelete(users);
        }
        return "redirect:table1";
    }
}
