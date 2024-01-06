package com.angelfg.pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class) // Tiene la capacidad de ser auditado por fecha de mofidicacion y actualizacion
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(unique = true, length = 100, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @Column(columnDefinition = "DECIMAL(8,2)", nullable = false)
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

}
