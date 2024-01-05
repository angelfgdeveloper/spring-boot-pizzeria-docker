package com.angelfg.pizzeria.persistence.repository;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import com.angelfg.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    // #1 forma para hacerlo
    // @Query(value = "UPDATE pizza SET price = :newPrice WHERE id_pizza = :idPizza", nativeQuery = true)
    // void updatePrice(@Param("idPizza") int idPizza, @Param("newPrice") double newPrice);

    // #2 forma para hacerlo (Spring special language)
    @Modifying // Para poder actualizar y eliminar
    @Query(value = "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}",
            nativeQuery = true
    )
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);

}
