package com.angelfg.pizzeria.web.controller;

import com.angelfg.pizzeria.persistence.entity.PizzaEntity;
import com.angelfg.pizzeria.service.PizzaService;
import com.angelfg.pizzeria.service.dto.UpdatePizzaPriceDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null || pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizza));
        }

        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if (pizzaService.exists(idPizza)) {
            pizzaService.delete(idPizza);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWith(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getWith(ingredient));
    }

    @GetMapping("/without/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWithout(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getWithout(ingredient));
    }

    @GetMapping("/name-first/{name}")
    public ResponseEntity<PizzaEntity> getFirstPizzaAvailableByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getFirstPizzaAvailableByName(name));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    // Paginacion
    @GetMapping("/page")
    public ResponseEntity<Page<PizzaEntity>> getAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements
    ) {
        return ResponseEntity.ok(pizzaService.getAllPageable(page, elements));
    }

    @GetMapping("/available/page")
    public ResponseEntity<Page<PizzaEntity>> getAvailablePageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int elements,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection
    ) {
        return ResponseEntity.ok(pizzaService.getAvailablePageable(page, elements, sortBy, sortDirection));
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto updatePizzaPriceDto) {
        if (pizzaService.exists(updatePizzaPriceDto.getPizzaId())) {
            pizzaService.updatePrice(updatePizzaPriceDto);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
