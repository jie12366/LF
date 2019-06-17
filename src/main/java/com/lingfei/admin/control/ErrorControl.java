package com.lingfei.admin.control;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author www.xyjz123.xyz
 * @date 2019/4/2 13:31
 */
@Controller
public class ErrorControl implements ErrorController {

    @ApiOperation("当访问不存在的页面时，就跳转到这个页面并进行提示")
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode(404,500)
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Integer status1 = 404;
        if (statusCode.equals(status1)) {
            return "404";
        }
        return "500";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
