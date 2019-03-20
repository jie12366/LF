package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.impl.AnnounceServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        List<Announce> lists =  announceService.getAllResult();
        PageInfo<Announce> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pages",pageInfo);
        return "announce/announce";
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
     * 接受post传来的id，修改该id的数据
     * @param id int
     * @param model Model
     * @return 编辑的页面
     */
    @GetMapping("/editContent")
    public String editContent(int id,Model model){
        Announce announce = announceService.getAnnounceById(id);
        if(announce != null){
            model.addAttribute("announce",announce);
            return "announce/editContent";
        }
        return "redirect:announce";
    }

    /**
     * 编辑完后，更新数据库的记录
     * @param announce Announce
     * @return 服务端跳转回announce.html
     */
    @PostMapping("updateContent")
    public String updateContent(Announce announce){
        announceService.updateAnnounce(announce);
        return "redirect:announce";
    }

    /**
     * 根据传来的id删除对应的记录
     * @param id int
     * @return 服务端跳转回announce.html
     */
    @GetMapping("/deleteContent")
    public String deleteContent(String id){
        String[] ids = id.split(",");
        List<Announce> announces = new ArrayList<>();
        for(int i = 0;i<ids.length;i++){
            int id1 = Integer.parseInt(ids[i]);
            announces.add(announceService.getAnnounceById(id1));
        }
        announceService.batchDelete(announces);
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

    @GetMapping("/toEmail")
    public String toEmail(){
        return "announce/sendEmail";
    }

    @RequestMapping("/addEmail")
    public String  addEmail(HttpServletRequest request) throws Exception{

        String theme = request.getParameter("theme");
        String text = request.getParameter("content");
        HttpSession session = request.getSession();
        String emails = session.getAttribute("tos").toString();
        String[] to = emails.split(",");
        announceService.sendEmail(theme,text,to);
        return "admin";
    }

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/selectUser")
    public String selectUser(Model model, @RequestParam(value = "start",defaultValue = "1") int start, @RequestParam(value = "size",defaultValue = "5") int size){
        List<User> users = userService.listUser();
        PageHelper.startPage(start,size,"id asc");
        PageInfo<User> pageInfo = new PageInfo<>(users);
        model.addAttribute("pages",pageInfo);
        return "announce/selectUser";
    }

    @GetMapping("/getSelect")
    public String getSelect(String id, HttpSession session){
        String[] ids = id.split(",");
        StringBuilder sb = new StringBuilder();
        for(String id1 : ids){
            int id2 = Integer.parseInt(id1);
            sb.append(userService.getUser(id2).getEmail()).append(",");
        }
        String  emails = sb.toString().substring(0,sb.length() - 1);
        session.setAttribute("tos",emails);
        return "announce/sendEmail";
    }
}
