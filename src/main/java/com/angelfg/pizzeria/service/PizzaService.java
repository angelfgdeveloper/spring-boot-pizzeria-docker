package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import com.angelfg.pizzeria.persistence.repository.PizzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        return pizzaRepository.findAllByAvailableTrueOrderByPriceAsc();
    }

    public PizzaEntity getByName(String name) {
        return pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }

}
