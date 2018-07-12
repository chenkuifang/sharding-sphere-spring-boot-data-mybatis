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

import com.example.spring.boot.mybatis.entity.Order;
import com.example.spring.boot.mybatis.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private OrderRepository orderRepository;

    public List<Order> list(Integer userId) {
        return orderRepository.list(userId);
    }

    public void add() {
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 5; i++) {
            Order order = new Order();
            // 根据userId % 2  决定数据库
            order.setUserId(2);
            order.setStatus("1");
            order.setGoodsId(123);
            orderRepository.insert(order);
        }
    }
}
