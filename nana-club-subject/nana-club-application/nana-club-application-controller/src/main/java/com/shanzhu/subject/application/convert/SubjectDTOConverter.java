package com.shanzhu.subject.application.convert;

import com.shanzhu.subject.application.dto.SubjectInfoDTO;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import org.mapstruct.factory.Mappers;

public interface SubjectDTOConverter {
    SubjectDTOConverter INSTANCE = Mappers.getMapper(SubjectDTOConverter.class);

    SubjectInfoBO convertDtoToBo(SubjectInfoDTO dto);
}
