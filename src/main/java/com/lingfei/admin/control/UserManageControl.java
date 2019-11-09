package com.lingfei.admin.control;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingfei.admin.entity.UserInfo;
import com.lingfei.admin.service.UserInfoService;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @date 2019/3/19 22:15
 */

@RestController
public class UserManageControl {

    @Resource
    UserInfoService userService;

    /**
     * 获取所有user数据，并分页
     *
     * @param start 开始页
     * @param size  每页的大小
     * @return table1.html
     */
    @ApiOperation("分页展示会员信息")
    @GetMapping("/users/{start}/{size}")
    public JsonResult getTable1(@PathVariable("start") int start, @PathVariable("size") int size) {
        PageHelper.startPage(start, size, "id asc");
        List<UserInfo> lists = userService.listUser();
        PageInfo<UserInfo> pageInfo = new PageInfo<>(lists);
        return JsonResult.success(pageInfo);
    }

    @ApiOperation("获取我的部门成员")
    @GetMapping("/depart/{uid}")
    public JsonResult getMember(@PathVariable("uid")String uid){
        UserInfo userInfo = userService.getUser(uid);
        List<UserInfo> userInfos = userService.getUserByName(userInfo.getDepart().substring(0,2));
        if (userInfos.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(userInfos);
        }
    }
}
