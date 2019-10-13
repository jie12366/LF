package com.lingfei.admin.control;

import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.OrderBallService;
import com.lingfei.admin.service.UserService;
import com.lingfei.admin.utils.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/9/24 21:33
 */
@RestController
public class OrderBallController {

    @Resource
    OrderBallService orderBallService;

    @Resource
    UserService userService;

    @ApiOperation("约球列表")
    @GetMapping("/order/list")
    public JsonResult orderList(){
        List<User> users = orderBallService.getList();
        return JsonResult.ok(users);
    }

    /**
     * 接受get或者post方法
     * @return announce.html
     */
    @ApiOperation("最终约球会员的分页展示")
    @GetMapping("/order/lastList")
    public JsonResult getOrderList() {
        List<User> users = orderBallService.getListByPriority();
        return JsonResult.ok(users);
    }

    @ApiOperation("约球")
    @PostMapping("/order/ball")
    public JsonResult orderBall(@RequestParam("uid")String uid){
        int res = orderBallService.order(uid);
        return JsonResult.ok(res);
    }

    @ApiOperation("取消约球")
    @PostMapping("/cancel/ball")
    public JsonResult cancelBall(@RequestParam("uid")String uid){
        int res = orderBallService.cancelOrder(uid);
        return JsonResult.ok(res);
    }

    @ApiOperation("管理员取消约球")
    @PostMapping("/cancelBall")
    public JsonResult cancelBallByManager(@RequestParam("uid")String uid){
        int res = orderBallService.cancelOrderByManager(uid);
        return JsonResult.ok(res);
    }

    @ApiOperation("开始约球")
    @GetMapping("/start/order")
    public JsonResult startOrder(){
        orderBallService.startOrder();
        return JsonResult.ok();
    }

    @ApiOperation("结束约球")
    @GetMapping("/stop/order")
    public JsonResult stopOrder(){
        orderBallService.stopOrder();
        List<User> users = orderBallService.getListByPriority();
        // 将未能约到球的人，取消约球
        for (int i = 12; i < users.size(); i++){
            userService.updateCount(-1, users.get(i).getUuid());
        }
        return JsonResult.ok();
    }
}