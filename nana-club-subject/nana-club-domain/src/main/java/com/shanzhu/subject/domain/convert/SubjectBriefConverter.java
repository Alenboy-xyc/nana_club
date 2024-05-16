package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.infra.basic.entity.SubjectBrief;
import com.shanzhu.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.factory.Mappers;

public interface SubjectBriefConverter {
    SubjectBriefConverter INSTANCE = Mappers.getMapper(SubjectBriefConverter.class);

    SubjectBrief convertBoToEntity(SubjectAnswerBO bo);
}
