package com.example.practicaltrainingprogram.event;
import com.alibaba.fastjson.JSONObject;
import com.example.practicaltrainingprogram.service.RemoteService;
import com.example.practicaltrainingprogram.service.WeeklyService;
import com.example.practicaltrainingprogram.service.entity.ACDataDO;
import com.example.practicaltrainingprogram.service.entity.COEDataDO;
import com.example.practicaltrainingprogram.service.entity.ONESDataDO;
import com.example.practicaltrainingprogram.service.entity.RemoteData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EventConsumer {
    @Resource
    WeeklyService weeklyService;
    @Resource
    RemoteService remoteService;
    @KafkaListener(topics = {"Template"})
    public void handleMessage(ConsumerRecord record) {
        if (record == null || record.value() == null) {
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        Integer status = weeklyService.queryStatus();
        //在库中查询周报状态,判断是否继续执行
        if (event == null || status == null || status == 1 || status == -1) {
            return;
        }
        //拉取数据
        RemoteData remoteData = remoteService.pullRemoteData();
        //处理数据
        ACDataDO acDataDO = remoteService.processACData(remoteData.getAcData());
        COEDataDO coeDataDO = remoteService.processCOEData(remoteData.getCoeData());
        ONESDataDO onesDataDO = remoteService.processONESData(remoteData.getOnesData());
        //持久化
        remoteService.saveACData(acDataDO);
        remoteService.saveCOEData(coeDataDO);
        remoteService.saveONESData(onesDataDO);
        //消息推送
        remoteService.pushMessage();
    }

}
