package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {



}
