package com.linglouyi.user.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * token模型
 * @author linglouyi
 */
@Data
public class Token<U> implements Serializable {

    /**
     * 标识
     */
    private String id;

    /**
     * 账号
     */
    private String userName;

    /**
     * 签发人
     */
    private String iss;

    /**
     * 签发时间
     */
    private Date time;

    /**
     * 登录ip
     */
    private String loginIp;

    /**
     * 过期时间
     */
    private long exp;

    /**
     * 最后操作时间
     */
    private Date lastTime;

    /**
     * 登录用户
     */
    private U user;

}
