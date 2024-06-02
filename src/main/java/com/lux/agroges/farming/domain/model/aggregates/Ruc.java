/**
 *
 * @summary
 * This class is a placeholder for the Book aggregate root.
 *  it is a JPA entity that will be used to persist the Book aggregate root in the database.
 */


package com.lux.agroges.farming.domain.model.aggregates;
import com.lux.agroges.farming.domain.model.commands.CreateRucCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Getter
@Entity
public class Ruc extends AbstractAggregateRoot<Ruc> {


    /**
     * @summary declares the id column that will be used as the primary key column of the Ruc entity.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String ruc;


    /**
     * @summary
     * declares a foreign key column that will be used to store the id of the email entity associated with the Ruc entity.
     * @param email
     * represents the email entity associated with the Ruc entity.
     */

    @Column(nullable = false)
    @Setter

    private String email;


    /**
     * @summary
     * declares a foreign key column that will be used to store the id of the ruc entity associated with the Ruc entity.
     * @param ruc
     * represents the ruc entity associated with the Ruc entity.
     */
    @Column(nullable = false)
    @Setter
    private Long phone;

    @Column(nullable = false)
    @Setter
    private String socialReason;


    protected Ruc() {
        /**
         * @summary
         * This constructor is used by JPA to instantiate the Ruc entity.
         * It should not be used directly by the application code.
         * It is protected to prevent its direct instantiation.
         */
    }

    public Ruc (CreateRucCommand commandRuc){

        /**
         * @summary
         * This constructor is used to create a new Ruc entity from the CreateRucCommand object.
         * @param command
         * represents the CreateRucCommand object used to create the Ruc entity.
         */
        this.ruc = commandRuc.ruc();
        this.email = commandRuc.email().email();
        this.phone = commandRuc.phone().phone();
        this.socialReason = commandRuc.socialReason().socialReason();

    }


}

