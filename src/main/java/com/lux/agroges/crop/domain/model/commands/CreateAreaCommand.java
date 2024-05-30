package com.lux.agroges.crop.domain.model.commands;

public record CreateAreaCommand(String Area) {
    public CreateAreaCommand{
        if (Area == null || Area.isBlank()) {
            throw new IllegalArgumentException("Area cannot be null or empty");
        }
    }
}
