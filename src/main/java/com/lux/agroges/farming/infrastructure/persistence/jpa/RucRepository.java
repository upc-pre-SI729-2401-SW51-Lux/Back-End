package com.lux.agroges.farming.infrastructure.persistence.jpa;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RucRepository extends JpaRepository<Ruc, Long>{

    /**
     * @summary
     * Method to get all RUCs in the database
     * @return
     * List of RUCs in the database
     */
    @Query("SELECT r FROM Ruc r")
    List<Ruc> getAll();

    /**
     * @summary
     * Method to get a RUC by its ID
     * @param id
     * @return
     * RUC with the given ID
     */
    Optional<Ruc> findRucById(Long id);
}
