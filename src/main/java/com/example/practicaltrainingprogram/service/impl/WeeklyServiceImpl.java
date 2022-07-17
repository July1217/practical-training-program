package com.example.practicaltrainingprogram.service.impl;

import com.example.practicaltrainingprogram.api.entity.TemplateGeneratedVO;
import com.example.practicaltrainingprogram.event.Event;
import com.example.practicaltrainingprogram.event.EventProducer;
import com.example.practicaltrainingprogram.service.WeeklyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeeklyServiceImpl implements WeeklyService {
    @Resource
    EventProducer eventProducer;
    @Override
    public Integer generateTemplate(TemplateGeneratedVO templateGeneratedVO) {
        try {
            //持久化

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
}
