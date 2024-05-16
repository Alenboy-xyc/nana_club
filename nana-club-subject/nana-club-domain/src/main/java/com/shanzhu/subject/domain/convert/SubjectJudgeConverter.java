package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.infra.basic.entity.SubjectJudge;
import com.shanzhu.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.factory.Mappers;

public interface SubjectJudgeConverter {
    SubjectJudgeConverter INSTANCE = Mappers.getMapper(SubjectJudgeConverter.class);

    SubjectJudge convertBoToEntity(SubjectAnswerBO bo);
}
