package com.angelfg.pizzeria.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pizza_order")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order", nullable = false)
    private Integer idOrder;

    @Column(nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime date;

    @Column(nullable = false, columnDefinition = "DECIMAL(8,2)")
    private Double total;

    @Column(nullable = false, columnDefinition = "CHAR(1)")
    private String method;

    @Column(name = "additional_notes", length = 200)
    private String additionalNotes;


    // RELACIONES #2
    @OneToOne(fetch = FetchType.LAZY) // LAZY no cargue esta relacion hasta que se use (Cuando llamamos el customer.getName() por ejemplo)
    @JoinColumn(
            name = "id_customer",
            referencedColumnName = "id_customer",
            insertable = false,
            updatable = false
    )
    @JsonIgnore
    private CustomerEntity customer;

    // Relacion circular (con nombre del OrderItemEntity.class => OrderEntity order)
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER) // EAGER => cuando utilicemos esta clase lo llame inmediatamente
    @OrderBy("price ASC") // podemos ordernar por atributo ASC - DESC hasta por queries nativos
    private List<OrderItemEntity> items;

}
