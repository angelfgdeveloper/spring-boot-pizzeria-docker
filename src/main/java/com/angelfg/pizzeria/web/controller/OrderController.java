package com.angelfg.pizzeria.web.controller;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import com.angelfg.pizzeria.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/today")
    public ResponseEntity<List<OrderEntity>> getTodayOrders() {
        return ResponseEntity.ok(orderService.getTodayOrders());
    }

    @GetMapping("/outside")
    public ResponseEntity<List<OrderEntity>> getOutsideOrders() {
        return ResponseEntity.ok(orderService.getOutsideOrders());
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderEntity>> getCustomerOrders(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getCustomerOrders(id));
    }

}
