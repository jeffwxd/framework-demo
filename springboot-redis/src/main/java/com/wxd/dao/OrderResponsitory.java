package com.wxd.dao;

import com.wxd.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderResponsitory extends JpaRepository<OrderEntity,Long>,
        JpaSpecificationExecutor<OrderEntity> {

}
