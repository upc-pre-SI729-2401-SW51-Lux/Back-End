package com.lux.agroges.crop.domain.model.commands;

public record CreateCropCommand(Long cropId,String cropCode,Long cropArea, Long cropCost) {
}
