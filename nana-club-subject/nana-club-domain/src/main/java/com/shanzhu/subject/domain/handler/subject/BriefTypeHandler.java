package com.shanzhu.subject.domain.handler.subject;

import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.common.enums.SubjectInfoTypeEnum;
import com.shanzhu.subject.domain.convert.SubjectBriefConverter;
import com.shanzhu.subject.domain.convert.SubjectRadioConverter;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectBrief;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import com.shanzhu.subject.infra.basic.service.SubjectBriefService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shanzhu
 * @description 单选的题目策略类
 * @create 2024/5/15
 */
public class BriefTypeHandler implements SubjectTypeHandler{
    @Resource
    private SubjectBriefService subjectBriefService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断题目的插入
        List<SubjectBrief> subjectBriefList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectBrief brief = SubjectBriefConverter.INSTANCE.convertBoToEntity(option);
            brief.setSubjectId(subjectInfoBO.getId());
            brief.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
            subjectBriefList.add(brief);
        });
        //批量插入
        subjectBriefService.batchInsert(subjectBriefList);
    }
}
