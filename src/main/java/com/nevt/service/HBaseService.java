package com.nevt.service;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: gzq
 * @Date: 2021/1/7 - 01 - 07 - 16:27
 * @Description: com.nevt.service
 */

@Component
@EnableScheduling
public class HBaseService {

    @Autowired
    private Connection hbaseConnection;

    @Autowired
    private DBService dbService;



    /*
     * 制氢厂数据写入HBase数据库表
     * 数据库表RowKey = <data_source_id>:<timestamp>
     * @param tableName 写出要添加列族的表名
     * @param dataStationType 填postgre数据库里面对应的字段
     */

    public void writeHydrogenFactory(String tableName, int dataStationType) throws IOException {

        Admin admin = hbaseConnection.getAdmin();

        try{List<String> columnFamily = dbService.getColumnFamily(dataStationType);
        System.out.println(2);
        System.out.println(columnFamily);
        if (admin.tableExists(TableName.valueOf(tableName))) {
            ifTableExist(columnFamily, admin, tableName);
        } else {
            ifTableNotExist(columnFamily, admin, tableName);
        }}finally {
            admin.close();
        }
    }

    private void ifTableExist(List<String> columnFamily, Admin admin, String tableName) {
        for (String column : columnFamily) {
            System.out.println("Table Exist！");
            //如果没有表就要创建表用如下方法
            HColumnDescriptor newFamily = new HColumnDescriptor(column.getBytes());
            System.out.println(1);
            //try catch的原因：有可能该字段之前已经添加过了，就不用添加了，但是有些没添加的还要添加，所以先在这里把异
            // 常处理掉，后面的字段可以进行添加，不处理的话后面的字段加不上，这里直接抛出异常
            try {
                admin.addColumn(TableName.valueOf(tableName), newFamily);
            } catch (IOException e) {
                e.getMessage();

            }
            System.out.println("ColumnFamily has added!");
        }
    }

    private void ifTableNotExist(List<String> columnFamily, Admin admin, String tableName) throws IOException {
        System.out.println("Table Not Exist!");
        HTableDescriptor tableCreate = new HTableDescriptor(TableName.valueOf(tableName));
        for (String column : columnFamily) {
            System.out.println(column);
            HColumnDescriptor columnName = new HColumnDescriptor(column.getBytes());
            tableCreate.addFamily(columnName);
        }
        admin.createTable(tableCreate);
        System.out.println("Table and columnFamily have established!");
    }
}
