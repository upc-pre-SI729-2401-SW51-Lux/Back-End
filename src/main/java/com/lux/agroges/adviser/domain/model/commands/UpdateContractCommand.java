package com.lux.agroges.adviser.domain.model.commands;

public record UpdateContractCommand(Long contractId, String companyName, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
