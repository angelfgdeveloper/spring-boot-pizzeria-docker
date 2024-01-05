package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import com.angelfg.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.angelfg.pizzeria.persistence.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final Logger log = LoggerFactory.getLogger(PizzaService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaPagSortRepository pizzaPagSortRepository;

    public List<PizzaEntity> getAll() {
        // return jdbcTemplate.query("SELECT * FROM pizza WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));

        return pizzaRepository.findAll();
    }

    public PizzaEntity get(int idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza) {
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        pizzaRepository.deleteById(idPizza);
    }

    public List<PizzaEntity> getAvailable() {
        log.debug("Numero de pizzas veganas: " + pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public PizzaEntity getFirstPizzaAvailableByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("La pizza no existe"));
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    // Paginacion
    public Page<PizzaEntity> getAllPageable(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailablePageable(int page, int elements, String sortBy, String sortDirection) {

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy); // Filtrarlo por ordenamiento ASC - DESC

        // Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy)); => normal
        Pageable pageRequest = PageRequest.of(page, elements, sort);

        return pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

}
