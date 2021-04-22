package com.nevt.service;

import cn.hutool.core.collection.SpliteratorUtil;
import com.nevt.db.repository.DeviceTypeRepository;
import com.nevt.db.repository.entity.DeviceType;
import org.junit.Test;
//这个测试不了
//类上要有 SpringBootTest()注解  方法上Test()  我没有添加SpringBoot 测试的 Maven依赖
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class DBService {

    @Resource
    private DeviceTypeRepository deviceTypeRepository;

    /**
     * @param dataStationType dataStationType
     *                        10001 制氢厂        hydrogen_factory_data
     *                        10002 加氢站        hydrogen_station_data
     *                        10003 燃料电池车    hydrogen_vehicle_data
     * @return List<String> 列族名
     */

    public List<String> getColumnFamily(int dataStationType) {
        List<String> result = new ArrayList<>();
        List<DeviceType> deviceTypeList = deviceTypeRepository.findAll();
        for (DeviceType deviceType : deviceTypeList) {
            System.out.println(deviceType);
            if (deviceType.getDataStationTypeId() == dataStationType) {
                result.add(deviceType.getColumnFamily());
            }
        }
        return result;
    }

//    @Scheduled(fixedDelay = 100000)
//    public void getColumnFamily1() {
//
//        List<DeviceType> deviceTypeList = deviceTypeRepository.findAll();
////        System.out.println(deviceTypeList);
//        for (DeviceType deviceType : deviceTypeList) {
//            System.out.println(deviceType);
//            }
//        }
    }


