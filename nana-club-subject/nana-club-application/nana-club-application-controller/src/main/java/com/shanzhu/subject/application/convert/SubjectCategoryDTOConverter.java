package com.shanzhu.subject.application.convert;

import com.shanzhu.subject.application.dto.SubjectCategoryDTO;
import com.shanzhu.subject.domain.entity.SubjectCategoryBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryDTOConverter {
    SubjectCategoryDTOConverter INSTANCE = Mappers.getMapper(SubjectCategoryDTOConverter.class);

    SubjectCategoryBO convertDtoToCategoryBo(SubjectCategoryDTO subjectCategoryDTO);

    List<SubjectCategoryDTO> convertBoListToDtoList(List<SubjectCategoryBO> subjectCategoryBOList);
}
