package com.angelfg.pizzeria.persistence.audit;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    // Se utilizan solo con void
    // Si queremos que intervenga lo añadimos a su entity

    private PizzaEntity currentValue;

    @PostLoad // Se ejecuta antes de hacer una insercion
    public void postLoad(PizzaEntity pizzaEntity) {
        System.out.println("Antes de ejecutar insercion o select: " + pizzaEntity.toString());
        this.currentValue = SerializationUtils.clone(pizzaEntity); // Clono la entity en la pizza
    }

    @PostPersist // Creacion y
    @PostUpdate // Actualizacion vendra aca
    public void onPostPersist(PizzaEntity pizzaEntity) {
//        System.out.println("Crear o actualizar: " + pizzaEntity.toString());
        System.out.println("POST PERSIST OR UPDATE");

        if (this.currentValue != null) {
            System.out.println("OLD VALUE: " + this.currentValue.toString());
        } else {
            System.out.println("OLD VALUE: " + this.currentValue);
        }

        System.out.println("NEW VALUE: " + pizzaEntity.toString());
    }

    @PreRemove // Antes de eliminar en DB
    public void onPreDelete(PizzaEntity pizzaEntity) {
        System.out.println("Antes de eliminar: " + pizzaEntity.toString());
    }

}
