package com.shanzhu.subject.domain.service.impl;

import com.shanzhu.subject.domain.convert.SubjectConverter;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.domain.handler.subject.SubjectTypeHandler;
import com.shanzhu.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.shanzhu.subject.domain.service.SubjectDomainService;
import com.shanzhu.subject.infra.basic.entity.SubjectInfo;
import com.shanzhu.subject.infra.basic.entity.SubjectMapping;
import com.shanzhu.subject.infra.basic.service.SubjectInfoService;
import com.shanzhu.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shanzhu
 * @description 题目信息Domain
 * @create 2024/5/15
 */
@Service
@Slf4j
public class SubjectDomainServiceImpl implements SubjectDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;
    @Resource
    private SubjectMappingService subjectMappingService;
    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Override
    public void add(SubjectInfoBO bo) {
        if (log.isInfoEnabled()) {
            log.info("SubjectDomainServiceImpl.add.bo:{}", bo);
        }
        //工厂+策略模式开发
        SubjectInfo subjectInfo = SubjectConverter.INSTANCE.convertBoToEntity(bo);
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        handler.add(bo);
        //插入映射
        List<Integer> categoryIds = bo.getCategoryIds();
        List<Integer> labelIds = bo.getLabelIds();
        List<SubjectMapping> subjectMappingList = new ArrayList<>();
        categoryIds.forEach(categoryId -> {
            labelIds.forEach(labelId -> {
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfo.getId());
                subjectMapping.setCategoryId(Long.valueOf(categoryId));
                subjectMapping.setLabelId(Long.valueOf(labelId));
                subjectMappingList.add(subjectMapping);
            });
        });
        //批量插入映射
        subjectMappingService.batchInsert(subjectMappingList);
    }
}
