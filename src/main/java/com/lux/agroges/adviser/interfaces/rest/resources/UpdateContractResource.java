package com.lux.agroges.adviser.interfaces.rest.resources;

public record UpdateContractResource(String companyName, String contractorDni, Integer applicationTime, Double averageSalary, String description, String status) {
}
