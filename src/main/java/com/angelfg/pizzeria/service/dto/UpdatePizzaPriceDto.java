package com.angelfg.pizzeria.service.dto;

import lombok.Data;

@Data // Añade los getters, setter, constructor
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;
}
