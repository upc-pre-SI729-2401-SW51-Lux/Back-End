package com.lux.agroges.crop.domain.model.aggregates;
import com.lux.agroges.crop.domain.model.commands.CreateAreaCommand;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;


@Entity
public class Area extends AbstractAggregateRoot<Area> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String area;

    protected Area() {
    }

    /**
     public Area(CreateAreaCommand command) {

     //this.area=command.area;
     }
     **/
}