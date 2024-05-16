package com.shanzhu.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.shanzhu.subject.application.convert.SubjectCategoryDTOConverter;
import com.shanzhu.subject.application.dto.SubjectCategoryDTO;
import com.shanzhu.subject.common.entity.Result;
import com.shanzhu.subject.domain.entity.SubjectCategoryBO;
import com.shanzhu.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shanzhu
 * @description 题目分类controller
 * @create 2024/4/29
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 新增分类
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDTO dto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(dto));
            }
            //参数校验
            Preconditions.checkNotNull(dto.getCategoryName(), "分类名称不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(dto);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        }catch (Exception e) {
            return Result.error();
        }
    }

    /**
     * 查询大类下分类
     * @return
     */
    @GetMapping("/queryCategory")
    public Result queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "分类id不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类类型不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> dtoList = SubjectCategoryDTOConverter.INSTANCE.convertBoListToDtoList(subjectCategoryBOList);
            return Result.ok(dtoList);
        }catch (Exception e) {
            log.info("SubjectCategoryController.queryPrimaryCategory_error");
            return Result.error();
        }
    }

    /**
     * 更新
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean isUpdate = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(isUpdate);
        }catch (Exception e) {
            log.info("SubjectCategoryController.update_error,message:{}", e.getMessage());
            return Result.error();
        }
    }

    /**
     * 删除
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "id不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean isUpdate = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(isUpdate);
        }catch (Exception e) {
            log.info("SubjectCategoryController.delete_error,message:{}", e.getMessage());
            return Result.error();
        }
    }
}
