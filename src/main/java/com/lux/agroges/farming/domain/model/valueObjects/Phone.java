package com.lux.agroges.farming.domain.model.valueObjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

/**
 * @param phone
 */
public record Phone(Long phone) {

    /**
     * @summary
     * Declare the Phone class, which is a record class that represents the phone value object.
     * @param phone represents the phone to be created.
     */
  @JsonCreator
    public static Phone of(Long phone) {
        return new Phone(phone);
    }
    public Phone {
        if (phone == null ||phone < 100000000) {
            throw new IllegalArgumentException("Phone cannot be null or less than 9 digits");
        }
    }


}
