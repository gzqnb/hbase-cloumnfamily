package com.nevt.db.repository;

import com.nevt.db.repository.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * (DeviceType)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-28 15:50:04
 */
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer>,
        JpaSpecificationExecutor<DeviceType> {

}