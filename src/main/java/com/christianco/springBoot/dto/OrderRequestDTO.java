package com.christianco.springBoot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class OrderRequestDTO {

    @NotEmpty(message = "La orden debe tener al menos un producto")
    @Valid
    private List<OrderItemRequestDTO> items;

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}
