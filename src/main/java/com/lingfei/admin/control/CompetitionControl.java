package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.service.impl.CompetitionServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/22 13:28
 */
@Controller
public class CompetitionControl {

    @Autowired
    CompetitionServiceImpl competitionService;

    @ApiOperation("比赛报名信息的分页展示")
    @GetMapping("/table2")
    public String getTable2(Model model, @RequestParam(value = "start", defaultValue = "1") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<Competition> lists = competitionService.listCompetition();
        PageInfo<Competition> pageInfo = new PageInfo<>(lists);
        model.addAttribute("pages", pageInfo);
        return "table2/table2";
    }

    /**
     * 根据传来的id更改对应的数据
     *
     * @param id    int
     * @param model Model
     * @return editCompetition.html
     */
    @ApiOperation("根据传来的id获取数据，并把数据传到页面")
    @GetMapping("/competition/edit")
    public String editCompetition(int id, Model model) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition != null) {
            model.addAttribute("competition", competition);
            return "table2/editCompetition";
        }
        return "redirect:/table2";
    }

    /**
     * 接受post方法，将表单传来的数据插入
     *
     * @param competition com.lingfei.admin.entity.Competition
     * @return 服务端跳转到announce.html
     */
    @ApiOperation(value = "将表单传来的数据插入数据库")
    @PostMapping("/competition/add")
    public String addCompetition(Competition competition) {
        competitionService.saveCompetition(competition);
        return "redirect:/";
    }

    /**
     * 编辑完数据后，提交到这进行数据库的更新
     *
     * @param competition Competition
     * @return 返回table2表
     */
    @ApiOperation(value = "将表单传来的数据更新到数据库")
    @PostMapping("/competition/update")
    public String updateCompetition(Competition competition) {
        competitionService.updateCompetition(competition);
        return "redirect:/table2";
    }

    /**
     * 根据get传来的id删除对应的数据
     *
     * @param id inr
     * @return 返回table2表
     */
    @ApiOperation(value = "根据id删除对应的比赛报名信息")
    @GetMapping("/competition/delete")
    public String deleteCompetition(String id) {
        if (!id.matches(",")) {
            int i = Integer.parseInt(id);
            competitionService.deleteCompetition(i);
        } else {
            String[] id3 = id.split(",");
            List<Competition> competitions = new ArrayList<>();
            for (String id1 : id3) {
                int id2 = Integer.parseInt(id1);
                competitions.add(competitionService.getCompetitionById(id2));
            }
            competitionService.batchDeleteCompetition(competitions);
        }
        return "redirect:/table2";
    }
}
