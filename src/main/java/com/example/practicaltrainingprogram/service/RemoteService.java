package com.example.practicaltrainingprogram.service;

import com.example.practicaltrainingprogram.service.entity.*;

public interface RemoteService {
    //拉取数据
    RemoteData pullRemoteData();
    //数据处理
    ACDataDO processACData(ACData acData);
    ONESDataDO processONESData(ONESData onesData);
    COEDataDO processCOEData(COEData coeData);
    //数据持久化
    Integer saveACData(ACDataDO acDataDO);
    Integer saveONESData(ONESDataDO onesDataDO);
    Integer saveCOEData(COEDataDO coeDataDO);
    //消息推送
    void pushMessage();
}
