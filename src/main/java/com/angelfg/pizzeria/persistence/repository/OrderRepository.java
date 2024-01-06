package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.OrderEntity;
import com.angelfg.pizzeria.persistence.projection.OrderSummary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
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

    // Genera un 20% a una pizza
    // Se llama a procedimiento almacenado con el nombre, y lo que va a regresar
    @Procedure(value = "take_random_pizza_order", outputParameterName = "order_taken")
    boolean saveRandomOrder(@Param("id_customer") String idCustomer, @Param("method") String method);

}

//DROP procedure IF EXISTS `take_random_pizza_order`;
//
//
//CREATE PROCEDURE `take_random_pizza_order`(	IN id_customer VARCHAR(15),
//IN method CHAR(1),
//OUT order_taken BOOL)
//BEGIN
//DECLARE id_random_pizza INT;
//DECLARE price_random_pizza DECIMAL(5,2);
//DECLARE price_with_discount DECIMAL(5,2);
//
//DECLARE WITH_ERRORS BOOL DEFAULT FALSE;
//DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
//        BEGIN
//SET WITH_ERRORS = TRUE;
//END;
//
//        SELECT	id_pizza, price
//        INTO 	id_random_pizza, price_random_pizza
//FROM 	pizza
//WHERE 	available = 1
//ORDER BY RAND()
//LIMIT 	1;
//
//SET price_with_discount = price_random_pizza - (price_random_pizza * 0.20);
//
//START TRANSACTION;
//INSERT INTO pizza_order (id_customer, date, total, method, additional_notes)
//VALUES (id_customer, SYSDATE(), price_with_discount, method, '20% OFF PIZZA RANDOM PROMOTION');
//
//INSERT INTO order_item (id_item, id_order, id_pizza, quantity, price)
//VALUES (1, LAST_INSERT_ID(), id_random_pizza, 1, price_random_pizza);
//
//IF WITH_ERRORS THEN
//SET order_taken = FALSE;
//ROLLBACK;
//ELSE
//SET order_taken = TRUE;
//COMMIT;
//END IF;
//
//SELECT order_taken;
//END;



