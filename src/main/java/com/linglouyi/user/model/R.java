package com.linglouyi.user.model;

import com.linglouyi.user.constant.HandleState;
import lombok.Data;

/**
 * @author linglouyi
 * 统一返回模型
 */
@Data
public class R<M> {

    private Integer code;

    private String msg;

    private M data;

    public static R success(){
        R r = new R();
        r.setCode(HandleState.SUCCESS.getOrdinal());
        r.setMsg(HandleState.SUCCESS.getName());
        return r;
    }

    public static <M> R<M> success(M o){
        R<M> r = new R<>();
        r.setCode(HandleState.SUCCESS.getOrdinal());
        r.setMsg(HandleState.SUCCESS.getName());
        r.setData(o);
        return r;
    }

    public static <M> R<M> success(String msg, M o){
        R<M> r = new R<>();
        r.setCode(HandleState.SUCCESS.getOrdinal());
        r.setMsg(msg);
        r.setData(o);
        return r;
    }

    public static R success(String msg){
        R r = new R();
        r.setCode(HandleState.SUCCESS.getOrdinal());
        r.setMsg(msg);
        return r;
    }

    public static <M> R<M> error(){
        R<M> r = new R<M>();
        r.setCode(HandleState.ERROR.getOrdinal());
        r.setMsg(HandleState.ERROR.getName());
        return r;
    }

    public static R error(String msg){
        R r = new R();
        r.setCode(HandleState.ERROR.getOrdinal());
        r.setMsg(msg);
        return r;
    }

    public static <M> R<M> error(Integer code,String msg){
        R<M> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    public static <M> R<M> logout(){
        R<M> r = new R<>();
        r.setCode(HandleState.NO_LOGIN.getOrdinal());
        r.setMsg("没有登录");
        return r;
    }
}
