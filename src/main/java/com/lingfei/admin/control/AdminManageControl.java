package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.entity.CountVisitor;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.impl.AnnounceServiceImpl;
import com.lingfei.admin.service.impl.CompetitionServiceImpl;
import com.lingfei.admin.service.impl.UserServiceImpl;
import com.lingfei.admin.service.impl.VisitorServiceImpl;
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
@RequestMapping("/admin")
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
    @PostMapping("/updateContent")
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

    @Autowired
    VisitorServiceImpl visitorService;
    /**
     * 直接跳转,并设置访客数以及浏览数
     * @return admin.html
     */
    @GetMapping("/index")
    public String index(Model model){
        CountVisitor visitor = visitorService.getVisitorByDate();
        int totalVisitor = visitorService.getAllVisitor();
        int totalUser = userService.listUser().size();
        if(visitor == null){
            model.addAttribute("visitor",0);
        }else {
            model.addAttribute("visitor",visitor.getVisitor());
        }
        if (totalVisitor == 0){
            model.addAttribute("totalVisitor",0);
        }else {
            model.addAttribute("totalVisitor",totalVisitor);
        }
        if(totalUser == 0){
            model.addAttribute("totalUser",0);
        }else {
            model.addAttribute("totalUser",totalUser);
        }
        return "admin";
    }

    /**
     * 直接跳转
     * @return sendEmail.html
     */
    @GetMapping("/toEmail")
    public String toEmail(){
        return "announce/sendEmail";
    }

    /**
     * 添加发送邮件的信息
     * @param request HttpServletRequest
     * @return 服务端跳转到/announce
     * @throws Exception
     */
    @RequestMapping("/addEmail")
    public String  addEmail(HttpServletRequest request) throws Exception{

        String theme = request.getParameter("theme");
        String text = request.getParameter("content");
        HttpSession session = request.getSession();
        String emails = session.getAttribute("tos").toString();
        String[] to = emails.split(",");
        announceService.sendEmail(theme,text,to);
        return "redirect:announce";
    }

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    CompetitionServiceImpl competitionService;
    /**
     * 选择要群发邮件的对象
     * @param model Model
     * @param start1 用户表开始页
     * @param start2 报名表开始页
     * @param size 每页的大小
     * @return selectUser.html
     */
    @GetMapping("/selectUser")
    public String selectUser(Model model, @RequestParam(value = "start1",defaultValue = "1") int start1,@RequestParam(value = "start2",defaultValue = "1") int start2, @RequestParam(value = "size",defaultValue = "5") int size){
        List<User> users = userService.listUser();
        PageHelper.startPage(start1,size,"id asc");
        PageInfo<User> pageInfo1 = new PageInfo<>(users);
        model.addAttribute("pages1",pageInfo1);

        List<Competition> competitions = competitionService.listCompetition();
        PageHelper.startPage(start2,size,"id asc");
        PageInfo<Competition> pageInfo2 = new PageInfo<>(competitions);
        model.addAttribute("pages2",pageInfo2);
        return "announce/selectUser";
    }

    /**
     * 获取刚刚选择的群发的对象
     * @param emails String
     * @param session HttpSession
     * @return sendEmail.html
     */
    @GetMapping("/getSelect")
    public String getSelect(String emails, HttpSession session){
        emails.substring(0,emails.length() - 1);
        session.setAttribute("tos",emails);
        return "announce/sendEmail";
    }
}
