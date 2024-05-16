package com.shanzhu.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 */
@Getter
public enum CategoryTypeEnum {
    PRIMARY(1, "岗位大类"),
    SECOND(0, "二级分类");

    public int code;
    public String desc;

    CategoryTypeEnum(int code, String desc) {
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
