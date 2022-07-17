package com.example.practicaltrainingprogram.event;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {


    @KafkaListener(topics = {"Template"})
    public void handleMessage(ConsumerRecord record) {
        if (record == null || record.value() == null) {
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(), Event.class);
        if (event == null) {
            return;
        }
        //在库中查询周报状态,判断是否继续执行

        //
    }

}
