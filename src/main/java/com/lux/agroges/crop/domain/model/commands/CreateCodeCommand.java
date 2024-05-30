package com.lux.agroges.crop.domain.model.commands;

public record CreateCodeCommand(String code) {

    public CreateCodeCommand {
        if(code == null || code.isBlank()){
            throw new IllegalArgumentException("Code cannot be null or empty");
        }
    }
}
