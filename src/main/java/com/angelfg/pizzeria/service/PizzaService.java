package com.angelfg.pizzeria.service;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    private final Logger log = LoggerFactory.getLogger(PizzaService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PizzaEntity> getAll() {
        return jdbcTemplate.query("SELECT * FROM pizza WHERE available = 0", new BeanPropertyRowMapper<>(PizzaEntity.class));
    }

}
