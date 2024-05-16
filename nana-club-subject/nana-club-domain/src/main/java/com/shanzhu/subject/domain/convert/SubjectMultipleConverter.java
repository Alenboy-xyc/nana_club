package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.infra.basic.entity.SubjectMultiple;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.factory.Mappers;

public interface SubjectMultipleConverter {
    SubjectMultipleConverter INSTANCE = Mappers.getMapper(SubjectMultipleConverter.class);

    SubjectMultiple convertBoToEntity(SubjectAnswerBO bo);
}
