package com.shanzhu.subject.domain.convert;

import com.shanzhu.subject.domain.entity.SubjectCategoryBO;
import com.shanzhu.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectCategoryConverter {
    SubjectCategoryConverter INSTANCE = Mappers.getMapper(SubjectCategoryConverter.class);

    SubjectCategory convertBoToCategory(SubjectCategoryBO subjectCategoryBO);

    List<SubjectCategoryBO> convertEntityToCategoryBoList(List<SubjectCategory> subjectCategoryList);
}
