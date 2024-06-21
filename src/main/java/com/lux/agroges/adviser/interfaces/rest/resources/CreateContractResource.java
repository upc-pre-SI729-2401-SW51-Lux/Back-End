package com.lux.agroges.adviser.interfaces.rest.resources;

public record CreateContractResource(String companyName, String ruc, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
