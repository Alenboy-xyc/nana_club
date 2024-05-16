package com.shanzhu.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 */
@Getter
public enum IsDeletedFlagEnum {
    DELETE(1, "删除"),
    NO_DELETE(0, "未删除");

    public int code;
    public String desc;

    IsDeletedFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCodeEnum getByCode(int codeValue) {
        for (ResultCodeEnum value : ResultCodeEnum.values()) {
            if (value.code == codeValue) {
                return value;
            }
        }
        return null;
    }
}
