package com.shanzhu.subject.domain.handler.subject;

import com.shanzhu.subject.common.enums.IsDeletedFlagEnum;
import com.shanzhu.subject.common.enums.SubjectInfoTypeEnum;
import com.shanzhu.subject.domain.convert.SubjectRadioConverter;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import com.shanzhu.subject.infra.basic.service.SubjectRadioService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shanzhu
 * @description 单选的题目策略类
 * @create 2024/5/15
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler{

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //单选题目的插入
        List<SubjectRadio> subjectRadioList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectRadio radio = SubjectRadioConverter.INSTANCE.convertBoToEntity(option);
            radio.setSubjectId(subjectInfoBO.getId());
            radio.setIsDeleted(IsDeletedFlagEnum.NO_DELETE.code);
            subjectRadioList.add(radio);
        });
        //批量插入
        subjectRadioService.batchInsert(subjectRadioList);
    }
}
