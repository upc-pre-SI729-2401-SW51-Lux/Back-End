package com.lux.agroges.crop.Domain.Model.commands;

public record UpdateCropCommand(Long cropId,String cropCode, String cropCurrency, Long value,Long productId) {

}
