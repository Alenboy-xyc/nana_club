package com.shanzhu.subject.domain.service;

import com.shanzhu.subject.domain.entity.SubjectCategoryBO;

import java.util.List;

public interface SubjectCategoryDomainService {

    void add(SubjectCategoryBO subjectCategoryBO);

    /**
     * 查询岗位大类
     * @return
     */
    List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO);

    /**
     * 更新
     * @param subjectCategoryBO
     * @return
     */
    Boolean update(SubjectCategoryBO subjectCategoryBO);

    Boolean delete(SubjectCategoryBO subjectCategoryBO);
}
