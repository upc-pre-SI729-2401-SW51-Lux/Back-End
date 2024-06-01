package com.lux.agroges.employees.infrastructure.persistence.jpa.repositories;

import com.lux.agroges.employees.domain.model.aggregates.Employee;
import com.lux.agroges.employees.domain.model.valueobjects.EmailAddress;
import com.lux.agroges.employees.domain.model.valueobjects.EmployeeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(EmailAddress email);
    Optional<Employee> findByName(EmployeeName name);
    Optional<Employee> findByIdDocument(String idDocument);
}
