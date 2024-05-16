package com.shanzhu.subject.domain.handler.subject;

import com.shanzhu.subject.common.enums.ResultCodeEnum;
import com.shanzhu.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shanzhu
 * @description 题目类型工厂
 * @create 2024/5/15
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType) {
        ResultCodeEnum subjectTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(subjectTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (SubjectTypeHandler handler : subjectTypeHandlerList) {
            handlerMap.put(handler.getHandlerType(), handler);
        }
    }
}
