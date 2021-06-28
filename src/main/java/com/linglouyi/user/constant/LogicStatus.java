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
public enum LogicStatus implements EnumBase {
    /**
     * 表示正常状态0
     */
    DELETE("删除"),
    /**
     * 表示非法状态1
     */
    NORMAL("正常");

    @Setter
    private String name;

    @JsonValue
    @Override
    public int getOrdinal(){
        return this.ordinal();
    }
}
