package com.shanzhu.subject.application.convert;

import com.shanzhu.subject.application.dto.SubjectLabelDTO;
import com.shanzhu.subject.domain.entity.SubjectLabelBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {
    SubjectLabelDTOConverter INSTANCE = Mappers.getMapper(SubjectLabelDTOConverter.class);

    SubjectLabelBO convertDtoToCategoryBo(SubjectLabelDTO subjectLabelDTO);

    List<SubjectLabelDTO> convertBoListToDtoList(List<SubjectLabelBO> subjectLabelBOList);
}
