package com.nevt;

import com.nevt.db.repository.DeviceTypeRepository;
import com.nevt.service.AddService;
import com.nevt.service.DBService;
import com.nevt.service.HBaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class CreateColumnFamily {


    public static void main(String[] args) {

        ConfigurableApplicationContext contxt = SpringApplication.run(CreateColumnFamily.class);
        AddService addService = contxt.getBean(AddService.class);
        if (args.length<1){
            System.out.println("error");
        }
        try {
            addService.toWrite(args[0],Integer.parseInt(args[1]));
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
