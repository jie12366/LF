package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.UserService;
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
 * @date 2019/3/19 22:15
 */

@Controller
public class UserManageControl {

    @Autowired
    UserService userService;

    /**
     * 获取所有user数据，并分页
     *
     * @param model Model
     * @param start 开始页
     * @param size  每页的大小
     * @return table1.html
     */
    @ApiOperation("分页展示会员信息")
    @GetMapping("/table1")
    public String getTable1(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<User> lists = userService.listUser();
        PageInfo<User> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pages", pageInfo);
        return "table1/table1";
    }

    /**
     * 根据传来的id更改对应的数据
     *
     * @param id    int
     * @param model Model
     * @return editUser.html
     */
    @ApiOperation("根据传来的id获取数据，并把数据传到页面")
    @GetMapping("/user/edit")
    public String editUser(int id, Model model) {
        User user = userService.getUser(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "table1/editUser";
        }
        return "redirect:/table1";
    }

    @ApiOperation("根据传来的name模糊查询部门成员")
    @PostMapping("/user/find")
    public String editUser(@RequestParam("name") String name, Model model) {
        List<User> users = userService.getUserByName(name);
        model.addAttribute("pages",users);
        return "table1/esTable1";
    }

    @ApiOperation("将完善的个人信息更新到数据库")
    @PostMapping("/user/prefect")
    public String prefectUser(User user){
        int id = userService.getId(user.getAccount());
        user.setId(id);
        userService.updateUser(user);
        return "front/login";
    }

    /**
     * 编辑完数据后，提交到这进行数据库的更新
     *
     * @param user User
     * @return 返回table1表
     */
    @ApiOperation("编辑完数据后，提交到这进行数据库的更新")
    @PostMapping("/user/update")
    public String updateUser(User user) {
        userService.updateDynamicUser(user);
        return "redirect:/table1";
    }

    /**
     * 根据get传来的id删除对应的数据
     *
     * @param id inr
     * @return 返回table1表
     */
    @ApiOperation("删除用户")
    @GetMapping("/user/delet")
    public String deleteUser(String id) {
        String[] id3 = id.split(",");
        for (String id1 : id3) {
            int id2 = Integer.parseInt(id1);
            userService.deleteUser(id2);
        }
        return "redirect:/table1";
    }
}
