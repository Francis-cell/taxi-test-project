package com.zmr.servicePassengerUser.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 朱梦仁 franciszmr@foxmail.com
 * @version 1.0
 * @date 2023/4/2 15:15
 * @description 乘客表对应的实体类
 */
@Data
public class PassengerUser {
    /** id */
    private Long id;
    /** 创建/插入时间 */
    private LocalDateTime gmtCreate;
    /** 修改时间 */
    private LocalDateTime gmtModified;
    /** 乘客手机号 */
    private String passengerPhone;
    /** 乘客昵称(姓名) */
    private String passengerName;
    /** 乘客性别 */
    private byte passengerGender;
    /** 乘客状态 */
    private byte state;
}
