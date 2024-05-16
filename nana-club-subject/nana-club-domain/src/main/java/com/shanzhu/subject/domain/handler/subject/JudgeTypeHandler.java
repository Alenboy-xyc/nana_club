package com.shanzhu.subject.domain.handler.subject;

import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.common.enums.SubjectInfoTypeEnum;
import com.shanzhu.subject.domain.convert.SubjectBriefConverter;
import com.shanzhu.subject.domain.convert.SubjectJudgeConverter;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectBrief;
import com.shanzhu.subject.infra.basic.entity.SubjectJudge;
import com.shanzhu.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shanzhu
 * @description 单选的题目策略类
 * @create 2024/5/15
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Autowired
    private SubjectJudgeService subjectJudgeService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //判断题目的插入
        List<SubjectJudge> subjectJudgeList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectJudge judge = SubjectJudgeConverter.INSTANCE.convertBoToEntity(option);
            judge.setSubjectId(subjectInfoBO.getId());
            judge.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
            subjectJudgeList.add(judge);
        });
        //批量插入
        subjectJudgeService.batchInsert(subjectJudgeList);
    }
}
