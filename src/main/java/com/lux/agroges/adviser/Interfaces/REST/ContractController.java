package com.lux.agroges.adviser.Interfaces.REST;

import com.lux.agroges.adviser.Domain.services.ContractCommandService;
import com.lux.agroges.adviser.Domain.services.ContractQueryService;
import com.lux.agroges.adviser.Interfaces.REST.Resources.CreateContractResource;
import com.lux.agroges.adviser.Interfaces.REST.Resources.UpdateContractResource;
import com.lux.agroges.adviser.Interfaces.REST.Transform.ContractFromEntityAssembler;
import com.lux.agroges.adviser.Interfaces.REST.Transform.CreateContractCommandFromResourceAssembler;
import com.lux.agroges.adviser.Interfaces.REST.Transform.UpdateContractCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/Contract", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Contract", description = "Contract Management Endpoints")
public class ContractController {
    private final ContractCommandService contractCommandService;
    private final ContractQueryService contractQueryService;

    public ContractController(ContractCommandService contractCommandService, ContractQueryService contractQueryService) {
        this.contractCommandService = contractCommandService;
        this.contractQueryService = contractQueryService;
    }

    @PostMapping
    public ResponseEntity<CreateContractResource> createContract(@RequestBody CreateContractResource resource) {
        if (resource.ruc() == null) {
            return ResponseEntity.badRequest().build();
        }
        var createContractCommand = CreateContractCommandFromResourceAssembler.toCommandFromResource(resource);
        var contract = contractCommandService.handle(createContractCommand);
        if (contract.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var contractResource = ContractFromEntityAssembler.toResourceFromEntity(contract.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(contractResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreateContractResource> updateContract(@PathVariable Long id, @RequestBody UpdateContractResource resource) {
        var updateContractCommand = UpdateContractCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var contract = contractCommandService.handle(updateContractCommand);
        if (contract.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var contractResource = ContractFromEntityAssembler.toResourceFromEntity(contract.get());
        return ResponseEntity.status(HttpStatus.OK).body(contractResource);
    }

    @GetMapping
    public ResponseEntity<List<CreateContractResource>> getAllContracts() {
        var contracts = contractQueryService.getAllContracts();
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var contractResources = ContractFromEntityAssembler.toResourcesFromEntities(contracts);
        return ResponseEntity.ok(contractResources);
    }

    @GetMapping("/declined")
    public ResponseEntity<List<CreateContractResource>> getDeclinedContracts() {
        var contracts = contractQueryService.getContractsByStatus("declined");
        if (contracts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var contractResources = ContractFromEntityAssembler.toResourcesFromEntities(contracts);
        return ResponseEntity.ok(contractResources);
    }
}
