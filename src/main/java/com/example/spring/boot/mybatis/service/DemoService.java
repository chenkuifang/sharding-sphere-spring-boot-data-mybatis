/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.example.spring.boot.mybatis.service;

import com.example.spring.boot.mybatis.common.DateUtil;
import com.example.spring.boot.mybatis.entity.Order;
import com.example.spring.boot.mybatis.repository.OrderRepository;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DemoService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list(Integer userId) {
        return orderRepository.list(userId);
    }

    public List<Order> listByOrderId(String orderId) {
        return orderRepository.listByOrderId(orderId);
    }

    public void add() {
        System.out.println("1.Insert--------------");
        int userId = 10001;
        Order order;
        for (int i = 0; i < 5; i++) {
            order = new Order();
            // 根据userId % 2  决定数据库
            order.setOrderId(generatorOrderId(userId));
            order.setUserId(userId);
            order.setStatus("1");
            order.setTotalPrice(new BigDecimal(200));
            order.setPayPrice(new BigDecimal(200));
            order.setAmount(1);
            order.setBuyerName("quifar");
            order.setBuyerPhone("15817864015");
            orderRepository.insert(order);
        }
    }

    /**
     * 生成分布式订单编码
     * 当天日期+用户ID+4位随机数
     *
     * @return
     */
    private String generatorOrderId(int userId) {
        final String day = DateUtil.getDays();
        final int randomInt = RandomUtils.nextInt(1000, 9999);
        return day + userId + randomInt;
    }

}
