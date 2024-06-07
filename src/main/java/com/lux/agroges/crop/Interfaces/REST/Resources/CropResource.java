package com.lux.agroges.crop.Interfaces.REST.Resources;

import java.util.ArrayList;
import java.util.List;

public record CropResource(Long id,String code,String currency, Long value, List<Long> productPrice) {
    public CropResource(Long id, String code,String currency,Long value){
        this(id,code,currency,value,new ArrayList<>());
    }
}
