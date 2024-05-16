package com.shanzhu.subject.common.enums;

import lombok.Getter;

/**
 * 题目类型枚举
 * 1：单选，2：多选，3：判断，4，简答
 */
@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");


    public int code;
    public String desc;

    SubjectInfoTypeEnum(int code, String desc) {
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
