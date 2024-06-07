/**
 * @summary
 * This class is a placeholder for the Email aggregate root.
 * it is a JPA entity that will be used to persist the Email aggregate root in the database.
 */

package com.lux.agroges.farming.domain.model.valueObjects;


import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @summary
 * Declare the Email class, which is a record class that represents the email value object.
 * @param email
 */
public record Email(String email) {

    /**
     * @summary
     * This constructor creates a new Email object with the given email.
     * @param email represents the email to be created.
     */
    @JsonCreator
    public static Email of(String email) {
        return new Email(email);
    }
    public Email {
        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email cannot be null or empty or must contain @ symbol");
        }
    }


}
