package com.lingfei.admin.control;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.lingfei.admin.service.UserService;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/9/22 18:15
 */
@Slf4j
@Controller
@RequestMapping("oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OauthController {

    private final AuthRequestFactory factory;

    @Resource
    UserService userService;

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     */
    @GetMapping("/login/{oauthType}")
    public void renderAuth(@PathVariable String oauthType, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(getAuthSource(oauthType));
        response.sendRedirect(authRequest.authorize(oauthType + "::" + AuthStateUtils.createState()));
    }

    /**
     * 登录成功后的回调
     *
     * @param oauthType 第三方登录类型
     * @param callback  携带返回的信息
     */
    @GetMapping("/{oauthType}/callback")
    public String login(@PathVariable String oauthType, AuthCallback callback,
                        HttpServletRequest request, Model model) throws IOException{
        AuthRequest authRequest = factory.get(getAuthSource(oauthType));
        // 登录
        AuthResponse response = authRequest.login(callback);
        // 将响应的数据格式化为字符串json
        String result = JSONUtil.toJsonStr(response);
        // 将字符串转化为json对象
        JSONObject jsonObject = JSONObject.parseObject(result);
        // 获取json对象中的data对象
        JSONObject data = jsonObject.getJSONObject("data");
        String uid = data.getString("uuid");
        // 判断是否已绑定QQ登录
        int res = userService.isExistsUuid(uid);
        // 没有绑定则存入uuid
        if (res == 0){
            String account = (String) request.getSession().getAttribute("user");
            userService.updateUuid(account,uid);
        }
        String account = userService.getUser(uid).getAccount();
        request.getSession().setAttribute("user",account);
        model.addAttribute("uid", uid);
        // 重定向到前端的约球页面
        return "front/order-ball";
    }

    private AuthSource getAuthSource(String type) {
        if (StrUtil.isNotBlank(type)) {
            return AuthSource.valueOf(type.toUpperCase());
        } else {
            throw new RuntimeException("不支持的类型");
        }
    }
}