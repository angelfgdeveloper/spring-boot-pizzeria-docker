package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import com.angelfg.pizzeria.persistence.projection.OrderSummary;
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

    // La unica regla es que debe contener de la proyeccion el nombre donde se almacenara la variable
    // GROUP_CONCAT => en mysql funciona para concatenar los nombre con , (comas)
    @Query(value =
            "SELECT po.id_order AS idOrder, " +
            "c.name AS customerName, " +
            "po.date AS orderDate, " +
            "po.total AS orderTotal, " +
            "GROUP_CONCAT(p.name) AS pizzaNames " +
            "FROM pizza_order po " +
            "INNER JOIN customer c ON po.id_customer = c.id_customer " +
            "INNER JOIN order_item oi ON po.id_order = oi.id_order " +
            "INNER JOIN pizza p ON oi.id_pizza = p.id_pizza " +
            "WHERE po.id_order = :orderId " +
            "GROUP BY po.id_order, c.name, po.date, po.total",
            nativeQuery = true
    )
    OrderSummary findSummary(@Param("orderId") int orderId);

}
