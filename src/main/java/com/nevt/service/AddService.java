package com.nevt.service;




import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
@Service
public class AddService {

    @Resource
    HBaseService hbaseService;
//     hbaseService.writeHydrogenFactory("data:hydrogen_factory_data",10001);
////        hbaseService.writeHydrogenFactory("data:hydrogen_station_data",10002);
////        hbaseService.writeHydrogenFactory("data:hydrogen_vehicle_data",10003);
////        hbaseService.writeHydrogenFactory("data:hydrogen_carrier_data",10004);

    public void toWrite(String tableName,Integer stationId) throws IOException {
        hbaseService.writeHydrogenFactory(tableName,stationId);
//


    }
}
