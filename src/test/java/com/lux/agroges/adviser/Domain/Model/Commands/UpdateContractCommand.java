package com.lux.agroges.adviser.Domain.Model.Commands;

public record UpdateContractCommand(Long contractId, String companyName, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
