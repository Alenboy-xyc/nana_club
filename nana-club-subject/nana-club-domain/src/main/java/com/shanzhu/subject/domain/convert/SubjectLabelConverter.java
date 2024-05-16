package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectLabelBO;
import com.shanzhu.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    SubjectLabelConverter INSTANCE = Mappers.getMapper(SubjectLabelConverter.class);

    SubjectLabel convertBoToLabelEntity(SubjectLabelBO subjectLabelBO);

    List<SubjectLabelBO> convertEntityListToBoList(List<SubjectLabel> subjectLabelList);
}
