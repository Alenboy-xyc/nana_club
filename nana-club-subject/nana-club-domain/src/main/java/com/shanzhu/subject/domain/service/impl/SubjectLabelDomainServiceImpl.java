package com.shanzhu.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.domain.convert.SubjectCategoryConverter;
import com.shanzhu.subject.domain.convert.SubjectLabelConverter;
import com.shanzhu.subject.domain.entity.SubjectLabelBO;
import com.shanzhu.subject.domain.service.SubjectLabelDomainService;
import com.shanzhu.subject.infra.basic.entity.SubjectCategory;
import com.shanzhu.subject.infra.basic.entity.SubjectLabel;
import com.shanzhu.subject.infra.basic.entity.SubjectMapping;
import com.shanzhu.subject.infra.basic.service.SubjectLabelService;
import com.shanzhu.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shanzhu
 * @description
 * @create 2024/5/9
 */
@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {
    @Autowired
    private SubjectLabelService subjectLabelService;
    @Autowired
    private SubjectMappingService subjectMappingService;

    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.dto:{}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBoToLabelEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
        int count = subjectLabelService.insert(subjectLabel);
        return count > 0;
    }

    public Boolean update(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBoToLabelEntity(subjectLabelBO);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE
                .convertBoToLabelEntity(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeletedFlagEnum.DELETE.code);
        int count = subjectLabelService.update(subjectLabel);
        return count > 0;
    }

    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
        //查询映射
        List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(subjectMappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelList = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabelList = subjectLabelService.batchQueryById(labelList);
        //组装BO
        List<SubjectLabelBO> subjectLabelBOList = new ArrayList<>();
        subjectLabelList.forEach(subjectLabel -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(subjectLabel.getId());
            bo.setLabelName(subjectLabel.getLabelName());
            bo.setSortNum(subjectLabel.getSortNum());
            bo.setCategoryId(categoryId);
            subjectLabelBOList.add(bo);
        });
        return subjectLabelBOList;
    }
}
