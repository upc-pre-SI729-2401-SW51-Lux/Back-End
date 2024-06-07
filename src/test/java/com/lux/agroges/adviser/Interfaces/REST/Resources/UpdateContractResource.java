package com.lux.agroges.adviser.Interfaces.REST.Resources;

public record UpdateContractResource(String companyName, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
