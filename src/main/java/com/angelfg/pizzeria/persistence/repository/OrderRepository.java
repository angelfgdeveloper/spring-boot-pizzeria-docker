package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends ListCrudRepository<OrderEntity, Integer> {

    // La fecha despues(after) de o antes(before) de
    List<OrderEntity> findAllByDateAfter(LocalDateTime date);
    List<OrderEntity> findAllByDateBefore(LocalDateTime date);

    // Consultar los metodos con In (requiere una lista)
    List<OrderEntity> findAllByMethodIn(List<String> methods);

    @Query(value = "SELECT * FROM pizza_order WHERE id_customer = :id", nativeQuery = true)
    List<OrderEntity> findCustomerOrders(@Param("id") String idCustomer);

}
