package com.linglouyi.user.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import com.linglouyi.user.constant.base.EnumBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author linglouyi
 */
@AllArgsConstructor
@Getter
public enum HandleState implements EnumBase {


    /**
     * 程序处理成功
     */
    SUCCESS("成功"),

    /**
     * 处理失败
     */
    ERROR("失败"),

    NO_LOGIN("没有登录");

    @Setter
    private String name;

    @JsonValue
    @Override
    public int getOrdinal(){
        return this.ordinal();
    }
}
