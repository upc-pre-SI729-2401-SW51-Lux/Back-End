package com.lux.agroges.adviser.Domain.Model.Commands;

public record CreateContractCommand(String companyName, String ruc, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
