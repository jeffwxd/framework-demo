package com.wxd.service.impl;

import com.example.demo01.utils.SpecificationBuilder;
import com.wxd.dao.OrderResponsitory;
import com.wxd.entity.OrderEntity;
import com.wxd.model.OrderBo;
import com.wxd.page.Pagination;
import com.wxd.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderResponsitory orderResponsitory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Pagination<OrderBo> getList(String beginTime, String endTime, Integer page, Integer size){

        ValueOperations<Object,OrderBo> operations = redisTemplate.opsForValue();

        //operations.get()
        SpecificationBuilder<OrderEntity> builder = SpecificationBuilder.builder();
        //添加查询条件
        builder.ge(OrderEntity.FIELD_FINISHED_TIME, beginTime)
                .le(OrderEntity.FIELD_FINISHED_TIME, endTime);

        //根据完成时间排序
        Sort sort = new Sort(Sort.Direction.DESC, "finishedTime");
        PageRequest pageData = PageRequest.of(page, size, sort);
        Page<OrderEntity> entityPage = orderResponsitory.findAll(builder.and(), pageData);
        List<OrderEntity> entities = entityPage.getContent();
        if (CollectionUtils.isEmpty(entities)) {
            return Pagination.build(new ArrayList<>(), 0);
        }
        List<OrderBo> result = new ArrayList<>();
        entities.forEach(e -> {
            result.add(new OrderBo(
                    e.getId(),
                    e.getShopId(),
                    e.getShopName(),
                    e.getMerchId(),
                    e.getMerchName()
            ));
        });
        return Pagination.build(result, entityPage.getTotalElements());
    }
}
