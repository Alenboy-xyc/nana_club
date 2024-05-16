package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.factory.Mappers;

public interface SubjectConverter {
    SubjectConverter INSTANCE = Mappers.getMapper(SubjectConverter.class);

    SubjectInfo convertBoToEntity(SubjectInfoBO bo);
}
