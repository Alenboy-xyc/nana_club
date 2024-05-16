package com.shanzhu.subject.domain.handler.subject;

import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.common.enums.SubjectInfoTypeEnum;
import com.shanzhu.subject.domain.convert.SubjectMultipleConverter;
import com.shanzhu.subject.domain.convert.SubjectRadioConverter;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectMultiple;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import com.shanzhu.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shanzhu
 * @description 多选的题目策略类
 * @create 2024/5/15
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler{
    @Autowired
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //多选题目的插入
        List<SubjectMultiple> subjectMultipleList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectMultiple multiple = SubjectMultipleConverter.INSTANCE.convertBoToEntity(option);
            multiple.setSubjectId(subjectInfoBO.getId());
            multiple.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
            subjectMultipleList.add(multiple);
        });
        //批量插入
        subjectMultipleService.batchInsert(subjectMultipleList);
    }
}
