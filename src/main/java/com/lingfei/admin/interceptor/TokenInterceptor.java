package com.lingfei.admin.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.lingfei.admin.annotation.LoginToken;
import com.lingfei.admin.entity.User;
import com.lingfei.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/6 20:40
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    UserService userService;

    @Resource
    ValueOperations<String ,String> valueOperations;

    private static final Logger log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从http请求头中取出token
        String token = request.getHeader("Authorization");

        //解决跨域问题
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.addHeader("Access-Control-Max-Age", "3600");

        //如果不是方法级别的请求，则直接通过
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        //利用java反射获取到请求的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果方法添加了LoginToken注解
        if(method.isAnnotationPresent(LoginToken.class)){
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()){
                //未登录
                if(token == null){
                    response.setStatus(401);
                    return false;
                }

                String token1 = "";
                // 账号密码登录的token
                if (token.split("\\.").length == 3){
                    String uid = "";
                    try {
                        uid = JWT.decode(token).getAudience().get(0);
                    }catch (JWTDecodeException jd){
                        log.warn("token get data error -- {}", jd);
                        //token取数据出问题
                        response.setStatus(401);
                        return false;
                    }

                    //获取用户信息
                    User users = userService.getUserId(Integer.parseInt(uid));

                    if (users == null){
                        log.warn("user is not exist");
                        //用户不存在
                        response.setStatus(401);
                        return false;
                    }
                    token1 = valueOperations.get(token.split("\\.")[2]);
                }else {
                    // 第三方登录的token
                    token1 = valueOperations.get(token);
                }
                //token过期(登录过期)
                if (token1 == null){
                    response.setStatus(403);
                    return false;
                }
                //token错误(未登录)
                if (!StringUtils.equals(token1,token)){
                    response.setStatus(401);
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}