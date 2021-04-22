package com.nevt.configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

import java.io.IOException;

@Configuration
public class HBaseConfig {

//    @Value("${hbase.zookeeper.quorum}")
//    private String zookeeperQuorum;

//    @Value("${hbase.zookeeper.property.clientPort}")
//    private String clientPort;
//
//    @Value("${zookeeper.znode.parent}")
//    private String znodeParent;

    @Bean
    public Connection hbaseConnection() throws IOException {
        System.out.println("creating HBase bean");
        org.apache.hadoop.conf.Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "nevt05:30102");
        configuration.set("hbase.rootdir", "hdfs://namenode:32010/HBase");
        configuration.set("hbase.regionserver.port", "172.20.240.91:16020");
        configuration.set("hbase.master.port", "172.20.240.91:32012");
        configuration.set("hbase.cluster.distributed", "true");
        Connection connection = ConnectionFactory.createConnection(configuration);
        return connection;
    }
}
