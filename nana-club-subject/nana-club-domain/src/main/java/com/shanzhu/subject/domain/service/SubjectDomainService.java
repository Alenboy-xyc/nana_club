package com.shanzhu.subject.domain.service;

import com.shanzhu.subject.domain.entity.SubjectInfoBO;

public interface SubjectDomainService {
    /**
     * 添加题目
     * @param bo
     * @return
     */
    void add(SubjectInfoBO bo);
}
