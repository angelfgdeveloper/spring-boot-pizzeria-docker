package com.angelfg.pizzeria.persistence.projection;

import java.time.LocalDateTime;

public interface OrderSummary {

    // Poner atributos en forma de get

    Integer getIdOrder();
    String getCustomerName();
    LocalDateTime getOrderDate();
    Double getOrderTotal();
    String getPizzaNames();

}
