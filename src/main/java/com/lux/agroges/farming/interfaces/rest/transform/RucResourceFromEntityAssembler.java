/**
 * @summary
 *  This class is used to transform a Ruc entity into a RucResource object.
 *  It is used to convert a Ruc entity into a RucResource object.
 *  The Ruc entity is converted into a RucResource object.
 */


package com.lux.agroges.farming.interfaces.rest.transform;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.interfaces.rest.resources.RucResource;

public class RucResourceFromEntityAssembler {

    /**
     * @summary
     *  This method is used to convert a Ruc entity into a RucResource object.
     *  It is used to transform a Ruc entity into a RucResource object.
     *  The Ruc entity is converted into a RucResource object.
     * @param ruc
     * represents the Ruc entity that will be converted into a RucResource object.
     * It is used to initialize the RucResource object with the data contained in the Ruc entity.
     * @return
     * returns a RucResource object that contains the data of the Ruc entity.
     */

    public static RucResource toResourceFromEntity(Ruc ruc){
        return new RucResource(ruc.getId(), ruc.getRuc(), ruc.getEmail(), ruc.getPhone());

    }
}
