package com.lux.agroges.farming.interfaces.rest.resources;


import com.lux.agroges.farming.domain.model.valueObjects.Email;
import com.lux.agroges.farming.domain.model.valueObjects.Phone;
import com.lux.agroges.farming.domain.model.valueObjects.SocialReason;

public record CreateRucResource(String ruc, Email email, Phone phone, SocialReason socialReason) {

    public CreateRucResource {
        if (ruc == null || ruc.isBlank()) {
            throw new IllegalArgumentException("Ruc cannot be null or empty");
        }
    }

}
