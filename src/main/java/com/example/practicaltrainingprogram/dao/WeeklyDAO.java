package com.example.practicaltrainingprogram.dao;

import com.example.practicaltrainingprogram.service.entity.TemplateGeneratedDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WeeklyDAO {
    int saveWeekly(TemplateGeneratedDO templateGeneratedDO);


}
