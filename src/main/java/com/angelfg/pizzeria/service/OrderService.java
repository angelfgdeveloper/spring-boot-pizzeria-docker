package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import com.angelfg.pizzeria.persistence.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAll() {
        List<OrderEntity> orders = orderRepository.findAll();

        // Como ya estamos utilizando el customer en el LAZY ahora si los llamara, es bajo demanda
        // orders.forEach(orden -> System.out.println(orden.getCustomer().getName()));

        // VALORES POR DEFECTO PARA FETCH
        // @OneToMany & @ManyToMany => LAZY
        // @ManyToOne & @OneToOne => EAGER

        return orders;
    }



}
