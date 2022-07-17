package com.example.practicaltrainingprogram.api.controller;

import com.example.practicaltrainingprogram.api.entity.ResponseWrapper;
import com.example.practicaltrainingprogram.api.entity.TemplateGeneratedVO;
import com.example.practicaltrainingprogram.api.validator.GeneratingTemplateValidator;
import com.example.practicaltrainingprogram.commons.enums.ErrorCode;
import com.example.practicaltrainingprogram.service.WeeklyService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;

@Controller
public class WeeklyController {
    @Resource
    WeeklyService weeklyService;
    @Resource
    GeneratingTemplateValidator generatingTemplateValidator;


    @PostMapping("")
    public ResponseWrapper<Integer> generateWeeklyReportTemplate(@RequestBody TemplateGeneratedVO templateGeneratedVO){
        //验证参数合法性,验证是否重复提交
        generatingTemplateValidator.validate(templateGeneratedVO);


        //调用业务方法
        Integer status = weeklyService.generateTemplate(templateGeneratedVO);

        return ResponseWrapper.success(status);

    }

}
