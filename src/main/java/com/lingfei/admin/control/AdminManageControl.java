package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.lingfei.admin.entity.Announce;
import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.entity.CountVisitor;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.service.impl.*;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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
     * @param size  每页的大小
     * @return announce.html
     */
    @ApiOperation("公告信息的分页展示")
    @GetMapping("/announce")
    public String getContent(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<Announce> lists = announceService.getAllResult();
        PageInfo<Announce> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pages", pageInfo);
        return "announce/announce";
    }

    @Autowired
    private UploadServiceImpl qiNiuService;

    /**
     * 接受post方法，将表单传来的数据插入
     *
     * @param announce com.lingfei.admin.entity.Announce
     * @return 服务端跳转到announce.html
     */
    @ApiOperation("将表单传到的数据插入到数据库")
    @PostMapping("/addContent")
    public String addContent(Announce announce, HttpServletRequest request,
                             @RequestParam("file") MultipartFile file, Model model) {
        try {
            //根据时间戳创建文件名
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //创建文件的实际路径
            String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;
            //根据文件路径创建文件对应的实际文件
            File destFile = new File(destFileName);
            //创建文件实际路径
            destFile.getParentFile().mkdirs();
            //将文件传到对应的文件位置
            file.transferTo(destFile);
            Response response = qiNiuService.uploadFile(destFile);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            announce.setPicture(putRet.key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        announceService.save(announce);
        return "redirect:announce";
    }

    /**
     * 接受post传来的id，修改该id的数据
     *
     * @param id    int
     * @param model Model
     * @return 编辑的页面
     */
    @ApiOperation("根据传来的id获取数据，并把数据传到页面")
    @GetMapping("/editContent")
    public String editContent(int id, Model model) {
        Announce announce = announceService.getAnnounceById(id);
        if (announce != null) {
            model.addAttribute("announce", announce);
            return "announce/editContent";
        }
        return "redirect:announce";
    }

    /**
     * 编辑完后，更新数据库的记录
     *
     * @param announce Announce
     * @return 服务端跳转回announce.html
     */
    @ApiOperation(value = "将表单传来的数据插入数据库")
    @PostMapping("/updateContent")
    public String updateContent(Announce announce) {
        announceService.updateAnnounce(announce);
        return "redirect:announce";
    }

    /**
     * 根据传来的id删除对应的记录
     *
     * @param id int
     * @return 服务端跳转回announce.html
     */
    @ApiOperation("根据id删除对应的公告信息")
    @GetMapping("/deleteContent")
    public String deleteContent(String id) {
        String[] ids = id.split(",");
        List<Announce> announces = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            int id1 = Integer.parseInt(ids[i]);
            announces.add(announceService.getAnnounceById(id1));
            try {
                qiNiuService.deleteFile(announceService.getAnnounceById(id1).getPicture());
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        }
        announceService.batchDelete(announces);
        return "redirect:announce";
    }

    @Autowired
    VisitorServiceImpl visitorService;

    @Autowired
    CompetitionServiceImpl competitionService;

    @Autowired
    UserService userService;
    /**
     * 直接跳转,并设置访客数以及浏览数
     *
     * @return admin.html
     */
    @ApiOperation("跳转到主页面，并给访客量、注册人数和报名人数赋值")
    @GetMapping("/index")
    public String index(Model model) {
        CountVisitor visitor = visitorService.getVisitorByDate();
        Object totalVisitor = visitorService.getAllVisitor();
        int totalUser = competitionService.listCompetition().size();
        int users = userService.listUser().size();
        if (visitor == null) {
            model.addAttribute("visitor", 0);
        } else {
            model.addAttribute("visitor", visitor.getVisitor());
        }
        if (totalVisitor == null) {
            model.addAttribute("totalVisitor", 0);
        } else {
            model.addAttribute("totalVisitor", totalVisitor);
        }
        if (totalUser == 0) {
            model.addAttribute("totalUser", 0);
        } else {
            model.addAttribute("totalUser", totalUser);
        }
        if (users == 0){
            model.addAttribute("users", 0);
        } else {
            model.addAttribute("users", users);
        }
        return "admin";
    }

    /**
     * 直接跳转
     *
     * @return sendEmail.html
     */
    @ApiOperation("跳转到发送邮件页面")
    @GetMapping("/toEmail")
    public String toEmail() {
        return "announce/sendEmail";
    }

    /**
     * 添加发送邮件的信息
     *
     * @param request HttpServletRequest
     * @return 服务端跳转到/announce
     * @throws Exception
     */
    @ApiOperation("添加邮件信息")
    @RequestMapping("/addEmail")
    public String addEmail(HttpServletRequest request) throws Exception {

        String theme = request.getParameter("theme");
        String text = request.getParameter("content");
        HttpSession session = request.getSession();
        String emails = session.getAttribute("tos").toString();
        String[] to = emails.split(",");
        for (String tos : to){
            System.out.println(tos);
        }
        announceService.sendEmail(theme, text, to);
        return "redirect:announce";
    }

    /**
     * 选择要群发邮件的对象
     *
     * @param model  Model
     * @return selectUser.html
     */
    @ApiOperation("跳转到一个页面，选择需要发送邮件的对象")
    @GetMapping("/selectUser")
    public String selectUser(Model model) {
        List<User> users = userService.listUser();
        model.addAttribute("pages1", users);
        List<Competition> competitions = competitionService.listCompetition();
        model.addAttribute("pages2", competitions);
        return "announce/selectUser";
    }

    /**
     * 获取刚刚选择的群发的对象
     *
     * @param emails  String
     * @param session HttpSession
     * @return sendEmail.html
     */
    @ApiOperation("获取刚刚选择的群发的对象")
    @GetMapping("/getSelect")
    public String getSelect(String emails, HttpSession session) {
        emails.substring(0, emails.length() - 1);
        System.out.println(emails);
        session.setAttribute("tos", emails);
        return "announce/sendEmail";
    }
}
