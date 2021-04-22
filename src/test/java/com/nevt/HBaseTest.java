package com.nevt;


import com.nevt.service.HBaseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import javax.annotation.Resource;
import java.io.IOException;

@SpringBootTest()
class HBaseTest {

    @Resource
    HBaseService hbaseService;


    @Test
    void testWrite() throws IOException {
//        hbaseService.writeHydrogenFactory("data:hydrogen_factory_data",10001);
//        hbaseService.writeHydrogenFactory("data:hydrogen_station_data",10002);
//        hbaseService.writeHydrogenFactory("data:hydrogen_vehicle_data",10003);
        hbaseService.writeHydrogenFactory("data:hydrogen_carrier_data",10004);
//


    }
}
