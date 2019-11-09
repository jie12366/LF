package com.lingfei.admin.control;

import com.lingfei.admin.entity.Competition;
import com.lingfei.admin.service.CompetitionService;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 20:27
 */
@RestController
public class CompetitionController {

    @Resource
    CompetitionService competitionService;

    @ApiOperation("比赛报名")
    @PostMapping("/competition")
    public JsonResult save(@RequestParam("aid")int aid, @RequestParam("item") String item,
                           @RequestParam("number")String number, @RequestParam("stuClass") String stuClass,
                           @RequestParam("qq")String qq,@RequestParam("phone")String phone, @RequestParam("name")String name){
        Competition competition = new Competition(aid,name,stuClass,number,qq,phone,item);

        if (competitionService.getCompetitionByPhone(phone) != null){
            return JsonResult.failure(ResultCode.USER_HAS_EXISTED);
        }
        int res = competitionService.saveCompetition(competition);
        if (res == 0){
            return JsonResult.failure(ResultCode.SAVE_ERROR);
        }else {
            return JsonResult.success();
        }
    }

    @ApiOperation("根据活动获取报名信息")
    @GetMapping("/competition/{aid}")
    public JsonResult getByAid(@PathVariable("aid")int aid){
        List<Competition> competitions = competitionService.listCompetition(aid);
        if (competitions.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success();
        }
    }
}