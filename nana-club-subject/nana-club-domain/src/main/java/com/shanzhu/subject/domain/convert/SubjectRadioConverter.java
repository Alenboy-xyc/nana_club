package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectInfo;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.factory.Mappers;

public interface SubjectRadioConverter {
    SubjectRadioConverter INSTANCE = Mappers.getMapper(SubjectRadioConverter.class);

    SubjectRadio convertBoToEntity(SubjectAnswerBO bo);
}
