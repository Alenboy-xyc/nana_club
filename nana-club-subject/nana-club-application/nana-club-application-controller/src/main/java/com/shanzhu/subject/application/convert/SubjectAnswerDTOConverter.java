package com.shanzhu.subject.application.convert;

import com.shanzhu.subject.application.dto.SubjectAnswerDTO;
import com.shanzhu.subject.application.dto.SubjectInfoDTO;
import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface SubjectAnswerDTOConverter {
    SubjectAnswerDTOConverter INSTANCE = Mappers.getMapper(SubjectAnswerDTOConverter.class);

    SubjectAnswerBO convertDtoToBo(SubjectAnswerDTO dto);

    List<SubjectAnswerBO> convertListDtoToBo(List<SubjectAnswerDTO> dtoList);
}
