package com.lingfei.admin.control;

import com.lingfei.admin.entity.Activity;
import com.lingfei.admin.service.ActivityService;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/9 14:45
 */
@RestController
public class ActivityController {

    @Resource
    ActivityService activityService;

    @ApiOperation("发布活动")
    @PostMapping("/activity")
    public JsonResult saveActivity(@RequestParam("title")String title,@RequestParam("zone")String zone,
                                   @RequestParam("date")String date,@RequestParam("picture")String picture,
                                   @RequestParam("content")String content){
        Activity activity = new Activity(title,zone,content,picture,date);
        int res = activityService.save(activity);
        if (res == 0){
            return JsonResult.failure(ResultCode.SAVE_ERROR);
        }else {
            return JsonResult.success();
        }
    }

    @ApiOperation("展示活动信息")
    @GetMapping("/activity")
    public JsonResult getActivity(){
        List<Activity> activities = activityService.getAllResult();
        if (activities.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(activities);
        }
    }

    @ApiOperation("获取一个活动信息")
    @GetMapping("/activity/{id}")
    public JsonResult getActivityById(@PathVariable("id") int id){
        Activity activity = activityService.getAnnounceById(id);
        if (activity == null){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(activity);
        }
    }
}