package com.lux.agroges.farming.domain.services;

import com.lux.agroges.farming.domain.model.aggregates.Ruc;
import com.lux.agroges.farming.domain.model.commands.CreateRucCommand;

import java.util.Optional;

public interface RucCommandService {

    Optional<Ruc> handle(CreateRucCommand command);
}
