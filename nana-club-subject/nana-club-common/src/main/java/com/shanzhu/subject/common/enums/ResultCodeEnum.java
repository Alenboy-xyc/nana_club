package com.shanzhu.subject.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(500,"失败"),
    SYSTEM_ERROR(501, "系统错误");

    public int code;
    public String desc;

    ResultCodeEnum(int code, String desc) {
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
