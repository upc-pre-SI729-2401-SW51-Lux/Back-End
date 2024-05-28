package com.lux.agroges.employees.domain.model.aggregates;

import com.lux.agroges.employees.domain.model.valuebojects.EmailAddress;
import com.lux.agroges.employees.domain.model.valuebojects.EmployeeDetails;
import com.lux.agroges.employees.domain.model.valuebojects.EmployeeName;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

public class Employee extends AbstractAggregateRoot<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    private EmployeeName name;

    @Embedded
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "workPosition", column = @Column(name = "work_position")),
            @AttributeOverride(name = "salary", column = @Column(name = "salary")),
            @AttributeOverride(name = "phone", column = @Column(name = "phone")),
            @AttributeOverride(name = "age", column = @Column(name = "age")),
            @AttributeOverride(name = "state", column = @Column(name = "state"))
    })
    private EmployeeDetails details;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Employee(String firstName, String lastName, String email, String workPosition, Integer salary, String phone, Integer age, String state) {
        this.name = new EmployeeName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.details = new EmployeeDetails(workPosition, salary, phone, age, state);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmployeeDetails() {
        return details.getEmployeeDetails();
    }

    public String getEmailAddress() {
        return email.address();
    }

}
