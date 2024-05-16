package com.shanzhu.subject.infra.basic.service;

import com.shanzhu.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-05-09 10:12:34
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据分类id查询标签
     * @param subjectLabel
     * @return
     */
    List<SubjectLabel> queryLabelByCategoryId(SubjectLabel subjectLabel);

    /**
     * 批量查询
     * @param labelList
     * @return
     */
    List<SubjectLabel> batchQueryById(List<Long> labelList);
}
