/**
 * @summary
 * This file contains the CreateRucCommand class, which is a record class that represents the command to create a new RUC.
 *  The class has a single field, ruc, which is a string that represents the RUC to be created.
 *  used record class as inmutable object
 */

package com.lux.agroges.farming.domain.model.commands;

import com.lux.agroges.farming.domain.model.valueObjects.Email;
import com.lux.agroges.farming.domain.model.valueObjects.Phone;
import com.lux.agroges.farming.domain.model.valueObjects.SocialReason;

public record CreateRucCommand (String ruc, Email email, Phone phone, SocialReason socialReason) {

    /**
     * @summary
     * This constructor creates a new CreateRucCommand object with the given RUC.
     * If the given RUC is null or empty, an IllegalArgumentException is thrown.
     * @param ruc
     * @throws IllegalArgumentException
     * @return CreateRucCommand
     */
    public CreateRucCommand {
        if (ruc == null || ruc.isBlank()) {
            throw new IllegalArgumentException("RUC cannot be null or empty");
        }
        if(email == null){
            throw new IllegalArgumentException("Ruc must have an email");
        }
        if(phone == null){
            throw new IllegalArgumentException("Ruc must have a phone");
        }
    }

}
