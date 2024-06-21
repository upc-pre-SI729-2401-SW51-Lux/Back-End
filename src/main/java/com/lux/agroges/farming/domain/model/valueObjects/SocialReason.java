/**
 * @summary
 * This file contains the SocialReason value object class. This class
 * is used to represent the social reason of a farmer.
 */

package com.lux.agroges.farming.domain.model.valueObjects;
import com.fasterxml.jackson.annotation.JsonCreator;

public record SocialReason(String socialReason) {

    /**
     * @summary
     * This method creates a new instance of the SocialReason class.
     *
     * @param socialReason The social reason of the farmer.
     * @return A new instance of the SocialReason class.
     */

    @JsonCreator
    public static SocialReason of(String socialReason) {
        return new SocialReason(socialReason);
    }

    /**
     * @summary
     * This method creates a new instance of the SocialReason class.
     *
     * @param socialReason The social reason of the farmer.
     * @throws IllegalArgumentException If the social reason is null or empty.
     */


    public SocialReason {
        if (socialReason == null || socialReason.isBlank()) {
            throw new IllegalArgumentException("Social reason cannot be null or empty");
        }
    }
}
