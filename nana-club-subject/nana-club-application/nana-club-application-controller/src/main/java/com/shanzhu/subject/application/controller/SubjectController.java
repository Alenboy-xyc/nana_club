package com.shanzhu.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.shanzhu.subject.application.convert.SubjectAnswerDTOConverter;
import com.shanzhu.subject.application.convert.SubjectDTOConverter;
import com.shanzhu.subject.application.convert.SubjectLabelDTOConverter;
import com.shanzhu.subject.application.dto.SubjectInfoDTO;
import com.shanzhu.subject.application.dto.SubjectLabelDTO;
import com.shanzhu.subject.common.entity.Result;
import com.shanzhu.subject.domain.entity.SubjectAnswerBO;
import com.shanzhu.subject.domain.entity.SubjectInfoBO;
import com.shanzhu.subject.domain.entity.SubjectLabelBO;
import com.shanzhu.subject.domain.service.SubjectDomainService;
import com.shanzhu.subject.infra.basic.entity.SubjectCategory;
import com.shanzhu.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanzhu
 * @description 刷题controller
 * @create 2024/4/29
 */
@RestController
@RequestMapping("/subject")
@Slf4j
public class SubjectController {

    @Autowired
    private SubjectDomainService subjectDomainService;

    /**
     * 新增题目
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectInfoDTO dto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(dto));
            }
            //参数校验
            Preconditions.checkNotNull(dto.getSubjectName(), "题目名称不能为空");
            //dto转bo
            SubjectInfoBO bo = SubjectDTOConverter.INSTANCE.convertDtoToBo(dto);
            List<SubjectAnswerBO> subjectAnswerBOList = SubjectAnswerDTOConverter.INSTANCE.convertListDtoToBo(dto.getOptionList());
            bo.setOptionList(subjectAnswerBOList);
            subjectDomainService.add(bo);
            return Result.ok();
        }catch (Exception e) {
            return Result.error();
        }
    }
}
