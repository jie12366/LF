package com.lingfei.admin.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * 验证码比对工具类
 * @author www.xyjz123.xyz
 * @date 2019/3/28 10:00
 */

public class VerifyUtil {
    /**
     * 将获取到的前端参数转为string类型
     * @param request HttpServletRequest
     * @param key String
     * @return
     */
    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if(result != null) {
                result = result.trim();
            }
            if("".equals(result)) {
                result = null;
            }
            return result;
        }catch(Exception e) {
            return null;
        }
    }
    /**
     * 验证码校验
     * @param request HttpServletRequest
     * @return 是否匹配
     */
    public static boolean checkVerifyCode(HttpServletRequest request) {
        //获取生成的验证码
        String verifyCodeExpected = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //获取用户输入的验证码
        String verifyCodeActual = VerifyUtil.getString(request, "verifyCode");
        if(verifyCodeActual == null ||!verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
