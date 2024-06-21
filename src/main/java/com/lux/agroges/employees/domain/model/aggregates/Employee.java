package com.lux.agroges.employees.domain.model.aggregates;

import com.lux.agroges.employees.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

@Getter
@Entity
public class Employee extends AbstractAggregateRoot<Employee> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "employee_name"))
    })
    private EmployeeName name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address"))
    })
    private EmailAddress email;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "idDocument", column = @Column(name = "id_employee_document")),
            @AttributeOverride(name = "workPosition", column = @Column(name = "work_position")),
            @AttributeOverride(name = "salary", column = @Column(name = "salary")),
            @AttributeOverride(name = "phone", column = @Column(name = "phone")),
            @AttributeOverride(name = "age", column = @Column(name = "age")),
            @AttributeOverride(name = "photoUrl", column = @Column(name = "photo_url"))
    })
    private EmployeeDetails details;

    private String idDocument;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Employee(String firstName, String lastName, String email, String idDocument, String workPosition, Integer salary, String phone, Integer age, String photoUrl) {
        this.name = new EmployeeName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.details = new EmployeeDetails(idDocument, workPosition, salary, phone, age, photoUrl);
    }

    public Employee() {

    }


    // other methods...

    public Employee updateEmployeeDetails(String workPosition, Integer salary, String phone, Integer age, String photoUrl) {
        this.details = new EmployeeDetails().updateEmployeeDetails(workPosition, salary, phone, age, photoUrl);
        return this;
    }

    public String getIdDocument() {
        return this.details.idDocument();
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
