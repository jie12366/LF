package com.lingfei.admin.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author www.xyjz123.xyz
 * @description 创建一个XssHttpServletRequestWrapper，通过重写getParameter()，
 * getParameterValues()和getHeader()方法来过滤HTTP请求中参数包含的恶意字符
 * @date 2019/4/5 14:57
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    HttpServletRequest orgRequest = null;
    private Boolean isIncludeRichText = false;

    public XssHttpServletRequestWrapper(HttpServletRequest request,Boolean isIncludeRichText){
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    /**
     * @author 熊义杰 www.xyj123.xyz
     * @description 覆盖getParameter方法，将参数名和参数值都做xss过滤
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     * @date 2019/4/5 16:04
     * @param [name]
     * @return java.lang.String
     **/
    @Override
    public String getParameter(String name){
        String content = "content";
        String WithHtml = "WithHtml";
        if((content).equals(name) || name.endsWith(WithHtml) || !isIncludeRichText){
            return super.getParameter(name);
        }
        //对name进行白名单过滤
        name = JsoupUtil.clean(name);
        String value = super.getParameter(name);
        if(StringUtils.isNotBlank(value)){
            value = JsoupUtil.clean(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name){
        String[] array = super.getParameterValues(name);
        if (array != null){
            for(int i = 0;i < array.length; i++){
                array[i] = JsoupUtil.clean(array[i]);
            }
        }
        return array;
    }

    /**
     * @author 熊义杰 www.xyj123.xyz
     * @description 覆盖getHeader方法，将参数名和参数值都做xss过滤
     * 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * @date 2019/4/5 16:18
     * @param [name]
     * @return java.lang.String
     **/
    @Override
    public String getHeader(String name){
        name = JsoupUtil.clean(name);
        String value = super.getHeader(name);
        if(StringUtils.isNotBlank(value)){
            value = JsoupUtil.clean(value);
        }
        return value;
    }

    /**
     * @author 熊义杰 www.xyj123.xyz
     * @description 获取原始的request
     * @date 2019/4/5 16:19
     * @param []
     * @return javax.servlet.http.HttpServletRequest
     **/
    @Override
    public HttpServletRequest getRequest(){
        return orgRequest;
    }

    /**
     * @author 熊义杰 www.xyj123.xyz
     * @description 获取原始的request的静态方法
     * @date 2019/4/5 16:26
     * @param [req]
     * @return javax.servlet.http.HttpServletRequest
     **/
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof XssHttpServletRequestWrapper) {
            return getOrgRequest(req);
        }
        return req;
    }
}