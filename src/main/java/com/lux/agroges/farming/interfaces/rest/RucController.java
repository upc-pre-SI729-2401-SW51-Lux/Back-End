package com.lux.agroges.farming.interfaces.rest;


import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.domain.model.queries.GetRucByIdQuery;
import com.lux.agroges.farming.domain.services.RucCommandService;
import com.lux.agroges.farming.domain.services.RucQueryService;
import com.lux.agroges.farming.interfaces.rest.resources.CreateRucResource;
import com.lux.agroges.farming.interfaces.rest.resources.RucResource;
import com.lux.agroges.farming.interfaces.rest.transform.CreateRucCommandFromResourceAssembler;
import com.lux.agroges.farming.interfaces.rest.transform.RucResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/farming/ruc")
public class RucController {

    private final RucQueryService rucQueryService;
    private final RucCommandService rucCommandService;

    public RucController(RucQueryService rucQueryService, RucCommandService rucCommandService) {
        this.rucQueryService = rucQueryService;
        this.rucCommandService = rucCommandService;
    }

    @GetMapping
    public ResponseEntity<List<Ruc>> getRucs() {
        return ResponseEntity.ok(rucQueryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RucResource> findRucById(@PathVariable Long id) {
        Optional<Ruc> ruc = rucQueryService.getRucById(new GetRucByIdQuery(id));
        return ruc.map(rucSearched -> ResponseEntity.ok(RucResourceFromEntityAssembler.toResourceFromEntity(rucSearched))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<RucResource> createRuc(@RequestBody CreateRucResource rucResource) {
        Optional<Ruc> rucOptional = rucCommandService.handle(CreateRucCommandFromResourceAssembler.toCommandFromResource(rucResource));
        return rucOptional.map(ruc -> new ResponseEntity<>(RucResourceFromEntityAssembler.toResourceFromEntity(ruc), CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}