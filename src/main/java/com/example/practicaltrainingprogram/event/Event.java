package com.example.practicaltrainingprogram.event;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Event {
    private String topic;
    private Map<String, Object> data = new HashMap<>();
}
