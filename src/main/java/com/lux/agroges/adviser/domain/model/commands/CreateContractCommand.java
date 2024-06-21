package com.lux.agroges.adviser.domain.model.commands;

public record CreateContractCommand(String companyName, String ruc, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
