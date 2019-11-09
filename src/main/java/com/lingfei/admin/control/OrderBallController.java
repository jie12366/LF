package com.lingfei.admin.control;

import com.lingfei.admin.entity.OrderItem;
import com.lingfei.admin.entity.UserInfo;
import com.lingfei.admin.service.OrderBallService;
import com.lingfei.admin.service.OrderItemService;
import com.lingfei.admin.service.UserInfoService;
import com.lingfei.admin.utils.JsonResult;
import com.lingfei.admin.utils.ResultCode;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
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
    UserInfoService userInfoService;

    @Resource
    OrderItemService orderItemService;

    @ApiOperation("约球列表")
    @GetMapping("/order/list")
    public JsonResult orderList(){
        List<UserInfo> users = orderBallService.getList();
        if (users.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(users);
        }
    }

    /**
     * 接受get或者post方法
     * @return announce.html
     */
    @ApiOperation("最终约球会员的分页展示")
    @GetMapping("/order/lastList")
    public JsonResult getOrderList() {
        List<UserInfo> users = orderBallService.getListByPriority();
        if (users.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(users);
        }
    }

    @ApiOperation("获取约球记录")
    @GetMapping("/orderItem/{uid}")
    public JsonResult getOrderItem(@PathVariable("uid")String uid){
        List<OrderItem> orderItems = orderItemService.getItem(uid);
        if (orderItems.isEmpty()){
            return JsonResult.failure(ResultCode.RESULE_DATA_NONE);
        }else {
            return JsonResult.success(orderItems);
        }
    }

    @ApiOperation("约球")
    @PostMapping("/order/ball")
    public JsonResult orderBall(@RequestParam("uid")String uid){
        int res = orderBallService.order(uid);
        orderItemService.saveItem(uid,new Date(),"约球成功");
        return getBack(res);
    }

    @ApiOperation("取消约球")
    @PostMapping("/cancel/ball")
    public JsonResult cancelBall(@RequestParam("uid")String uid){
        int res = orderBallService.cancelOrder(uid);
        orderItemService.saveItem(uid,new Date(),"取消约球");
        return getBack(res);
    }

    @ApiOperation("管理员取消约球")
    @PostMapping("/cancelBall")
    public JsonResult cancelBallByManager(@RequestParam("uid")String uid){
        int res = orderBallService.cancelOrderByManager(uid);
        orderItemService.saveItem(uid,new Date(),"约球被管理员取消");
        if (res == 1){
            return JsonResult.success(res);
        }else {
            return JsonResult.failure(ResultCode.ORDER_FAILURE);
        }
    }

    @ApiOperation("开始约球")
    @GetMapping("/start/order")
    public JsonResult startOrder(){
        orderBallService.startOrder();
        return JsonResult.success();
    }

    @ApiOperation("结束约球")
    @GetMapping("/stop/order")
    public JsonResult stopOrder(){
        orderBallService.stopOrder();
        List<UserInfo> users = orderBallService.getListByPriority();
        // 将未能约到球的人，取消约球
        for (int i = 12; i < users.size(); i++){
            userInfoService.updateCount(-1, users.get(i).getUuid());
            orderItemService.saveItem(users.get(i).getUuid(),new Date(),"约球被规则取消");
        }
        return JsonResult.success();
    }

    private JsonResult getBack(int res){
        if (res == 0){
            return JsonResult.failure(ResultCode.ORDER_FAILURE);
        }else if (res == 2){
            return JsonResult.failure(ResultCode.ORDER_EXIST);
        }else if (res == 3){
            return JsonResult.failure(ResultCode.ORDER_NOT_START);
        }else {
            return JsonResult.success();
        }
    }
}