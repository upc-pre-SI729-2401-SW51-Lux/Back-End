package com.lux.agroges.adviser.domain.model.aggregates;

import com.lux.agroges.adviser.domain.model.valueobjects.CompanyRuc;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Embedded
    @AttributeOverrides(
            {@AttributeOverride(name = "ruc", column = @Column(name = "company_ruc", nullable = false))}
    )
    private CompanyRuc ruc;

    private String contractorDni;
    private Integer applicationTime;
    private Double averageSalary;
    private String description;
    private String status;

    // Default constructor
    public Contract() {}

    // Full constructor
    public Contract(String companyName, String ruc, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
        this.companyName = companyName;
        this.ruc = new CompanyRuc(ruc);
        this.contractorDni = contractorDni;
        this.applicationTime = applicationTime;
        this.averageSalary = averageSalary;
        this.description = description;
        this.status = status;
    }

    // Update methods
    public Contract updateContract(String companyName, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
        this.companyName = companyName;
        this.contractorDni = contractorDni;
        this.applicationTime = applicationTime;
        this.averageSalary = averageSalary;
        this.description = description;
        this.status = status;
        return this;
    }
}
