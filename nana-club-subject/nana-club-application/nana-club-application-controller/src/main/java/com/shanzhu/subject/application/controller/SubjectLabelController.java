package com.shanzhu.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.shanzhu.subject.application.convert.SubjectLabelDTOConverter;
import com.shanzhu.subject.application.dto.SubjectLabelDTO;
import com.shanzhu.subject.common.entity.Result;
import com.shanzhu.subject.domain.entity.SubjectLabelBO;
import com.shanzhu.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shanzhu
 * @description 岗位标签
 * @create 2024/5/9
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {
    @Autowired
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增分类
     * @param dto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectLabelDTO dto) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}", JSON.toJSONString(dto));
            }
            //参数校验
            Preconditions.checkNotNull(dto.getLabelName(), "标签名称不能为空");
            Preconditions.checkNotNull(dto.getSortNum(), "排名下标不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToCategoryBo(dto);
            Boolean isSuccess = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(isSuccess);
        }catch (Exception e) {
            return Result.error();
        }
    }


    /**
     * 更新
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectLabelDTO);
            Boolean isUpdate = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(isUpdate);
        }catch (Exception e) {
            log.info("SubjectLabelController.update_error,message:{}", e.getMessage());
            return Result.error();
        }
    }


    /**
     * 删除
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectLabelDTO);
            Boolean isUpdate = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(isUpdate);
        }catch (Exception e) {
            log.info("SubjectLabelController.delete_error,message:{}", e.getMessage());
            return Result.error();
        }
    }


    /**
     * 根据分类id查询标签
     * @param subjectLabelDTO
     * @return
     */
    @GetMapping("/queryLabelByCategoryId")
    public Result queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            //参数校验
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToCategoryBo(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBoListToDtoList(subjectLabelBOList);
            return Result.ok(subjectLabelDTOList);
        }catch (Exception e) {
            log.info("SubjectLabelController.queryLabelByCategoryId_error,message:{}", e.getMessage());
            return Result.error();
        }
    }
}
