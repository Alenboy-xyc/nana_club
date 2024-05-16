package com.shanzhu.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.domain.convert.SubjectCategoryConverter;
import com.shanzhu.subject.domain.entity.SubjectCategoryBO;
import com.shanzhu.subject.domain.service.SubjectCategoryDomainService;
import com.shanzhu.subject.infra.basic.entity.SubjectCategory;
import com.shanzhu.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanzhu
 * @description
 * @create 2024/4/29
 */
@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {
    @Resource
    private SubjectCategoryService subjectCategoryService;

    public void add(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.dto:{}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
        subjectCategoryService.insert(subjectCategory);
    }

    /**
     * 查询岗位大类
     * @return
     */
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> boList = SubjectCategoryConverter.INSTANCE.convertEntityToCategoryBoList(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryPrimaryCategory.boList:{}", JSON.toJSONString(boList));
        }
        return boList;
    }

    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeletedFlagEnum.DELETE.code);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }
}
