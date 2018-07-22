package com.example.spring.boot.mybatis.controller;

import com.example.spring.boot.mybatis.entity.Order;
import com.example.spring.boot.mybatis.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * index
 *
 * @author QuiFar
 * @version V1.0
 **/
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private DemoService demoService;

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/list/{userId}")
    public List<Order> list(@PathVariable("userId") Integer userId) {
        return demoService.list(userId);
    }

    @GetMapping("/listByOrderId/{OrderId}")
    public List<Order> listByOrderId(@PathVariable("OrderId") String OrderId) {
        return demoService.listByOrderId(OrderId);
    }

    @GetMapping("/add")
    public void test() {
        demoService.add();
    }
}
