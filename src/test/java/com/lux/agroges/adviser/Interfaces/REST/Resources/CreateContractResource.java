package com.lux.agroges.adviser.Interfaces.REST.Resources;

public record CreateContractResource(String companyName, String ruc, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
