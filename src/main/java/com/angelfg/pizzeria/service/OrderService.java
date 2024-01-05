package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import com.angelfg.pizzeria.persistence.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    private final Logger log = LoggerFactory.getLogger(OrderService.class);

    public static final String DELIVERY = "D";
    public static final String CARRYOUT = "C";
    public static final String ON_SIDE = "S";

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

    public List<OrderEntity> getTodayOrders() {
        // En el tiempo donde se carga el tiempo 00:00:00
        LocalDateTime today = LocalDate.now().atTime(0,0);
        return orderRepository.findAllByDateAfter(today);
    }

    public List<OrderEntity> getOutsideOrders() {
        List<String> methods = Arrays.asList(DELIVERY, CARRYOUT);
        return orderRepository.findAllByMethodIn(methods);
    }

    public List<OrderEntity> getCustomerOrders(String idCustomer) {
        return orderRepository.findCustomerOrders(idCustomer);
    }

}
