package com.angelfg.pizzeria.persistence.audit;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass // Clase heredada
public class AuditableEntity {

    // Auditable
    @Column(name = "created_date")
    @CreatedDate
    // @JsonIgnore // Para no mostrar en la respuesta (solo para auditoria)
    private LocalDateTime createdDate; // Cuando se creo la pizza

    @Column(name = "modify_date")
    @LastModifiedDate
    // @JsonIgnore // Para no mostrar en la respuesta (solo para auditoria)
    private LocalDateTime modifyDate; // Cuando se modifico una pizza

}
