package com.lingfei.admin.utils;

/**
 * @author www.xyjz123.xyz
 * @description
 * @date 2019/11/6 20:03
 */
public enum ResultCode {

    /*成功状态码*/
    SUCCESS(200,"成功"),

    /* 参数错误：10001-19999 */
    PARAM_IS_BLANK(10002, "参数为空"),

    /* 用户错误：20001-29999*/
    USER_LOGIN_ERROR(20001, "密码错误"),
    USER_NOT_EXIST(20002, "用户不存在"),
    USER_HAS_EXISTED(20003, "用户已存在"),

    /* 业务错误：30001-39999 */
    SAVE_ERROR(30001,"保存发生错误"),
    UPDATE_ERROR(30002,"更新发生错误"),
    DELETE_ERROR(30003,"删除发生错误"),
    ORDER_FAILURE(30004,"约球失败"),
    ORDER_EXIST(30005,"已经约球了"),
    ORDER_NOT_START(30006,"约球未开始"),
    FILE_UPLOAD_ERROR(30007,"文件上传失败"),

    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    CAPTCHA_HAS_EXPIRED(50002,"验证码已过期"),
    CAPTCHA_IS_ERROR(50003,"验证码错误");

    /**状态码*/
    private Integer code;

    /**状态码描述*/
    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }
}