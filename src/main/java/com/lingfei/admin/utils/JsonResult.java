package com.lingfei.admin.utils;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

/**
 * 自定义响应数据结构
 * 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，不管多少个错误都以map形式返回
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 *
 * @author www.xyj123.xyz
 * @date 2019/3/30 13:57
 *  
 */
@Data
public class JsonResult {

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 响应中的数据
     */
    private Object data;

    /**
     * 响应中的业务状态
     */
    private int code;

    private JsonResult() {}

    /**
     * 成功，不带返回数据
     * @return JsonResult
     */
    public static JsonResult success(){
        JsonResult result = new JsonResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功，携带返回数据
     * @param data 要返回给用户的数据
     * @return JsonResult
     */
    public static JsonResult success(Object data){
        JsonResult result = new JsonResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败，携带错误信息
     * @param resultCode ResultCode
     * @return JsonResult
     */
    public static JsonResult failure(ResultCode resultCode) {
        JsonResult result = new JsonResult();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 设置响应码和对应的信息
     * @param code ResultCode
     */
    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.msg = code.message();
    }
}