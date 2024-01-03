package com.angelfg.pizzeria.web.controller;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import com.angelfg.pizzeria.service.PizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final Logger log = LoggerFactory.getLogger(PizzaController.class);

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(pizzaService.getAll());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getAll(@PathVariable int idPizza) {
        return ResponseEntity.ok(pizzaService.get(idPizza));
    }

}
