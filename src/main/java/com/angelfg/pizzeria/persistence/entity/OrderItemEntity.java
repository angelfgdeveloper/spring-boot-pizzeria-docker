package com.angelfg.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Id
    @Column(name = "id_item", nullable = false)
    private Integer idItem;

    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
    private Double quantity;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    // RELACIONES #1
    // Muchos items pueden tener una orden
    @ManyToOne
    @JoinColumn(
            name = "id_order",
            referencedColumnName = "id_order",
            insertable = false,
            updatable = false
    )
    private OrderEntity order;

    // Un order item solo puede tener una pizza
    @OneToOne
    @JoinColumn(
            name = "id_pizza",
            referencedColumnName = "id_pizza",
            insertable = false,
            updatable = false
    )
    private PizzaEntity pizza;

}
