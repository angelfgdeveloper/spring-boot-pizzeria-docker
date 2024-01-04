package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    // Query methods
    List<PizzaEntity> findAllByAvailableTrueOrderByPriceAsc();
    PizzaEntity findAllByAvailableTrueAndNameIgnoreCase(String name);

    // Convension mas utilizada
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

//    List<PizzaEntity> getAllBy();
//    List<PizzaEntity> queyBy();
//    List<PizzaEntity> searchAllBy();

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    int countByVeganTrue();

    // Solo obtiene un elemento (limit 1)
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    // El Top 3 son el numero de registros que van aparecer
    // LessThanEqual => menor o igual ...
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);

}
