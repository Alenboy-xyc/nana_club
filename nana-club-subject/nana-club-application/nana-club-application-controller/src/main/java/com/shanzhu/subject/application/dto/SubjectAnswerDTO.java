package com.shanzhu.subject.application.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)的答案DTO
 *
 * @author makejava
 * @since 2024-05-14 15:30:22
 */
public class SubjectAnswerDTO implements Serializable {
    /**
     * 答案选项标识
     */
    private Integer optionType;
    /**
     * 答案
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private Integer isCorrect;
}

