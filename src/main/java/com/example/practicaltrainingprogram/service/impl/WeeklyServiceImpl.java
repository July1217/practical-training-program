package com.example.practicaltrainingprogram.service.impl;

import com.example.practicaltrainingprogram.api.entity.TemplateGeneratedVO;
import com.example.practicaltrainingprogram.dao.WeeklyDAO;
import com.example.practicaltrainingprogram.event.Event;
import com.example.practicaltrainingprogram.event.EventProducer;
import com.example.practicaltrainingprogram.service.WeeklyService;
import com.example.practicaltrainingprogram.service.converter.WeeklConverter;
import com.example.practicaltrainingprogram.service.entity.TemplateGeneratedDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeeklyServiceImpl implements WeeklyService {
    @Resource
    EventProducer eventProducer;
    @Resource
    WeeklyDAO weeklyDAO;
    @Resource
    WeeklConverter weeklConverter;
    @Override
    public Integer generateTemplate(TemplateGeneratedVO templateGeneratedVO) {
        try {
            //类型转换
            TemplateGeneratedDO templateGeneratedDO = weeklConverter.converter2DO(templateGeneratedVO);
            //持久化
            weeklyDAO.saveWeekly(templateGeneratedDO);
            // 触发评论事件
            Event event = new Event();
            event.setTopic("Template");
            //数据map
            Map<String, Object> map = new HashMap<>();
            event.setData(map);

            eventProducer.fireEvent(event);
        }catch (Exception e){
            return -1;
        }
        return 0;
    }

    @Override
    public Integer queryStatus() {
        return null;
    }
}
