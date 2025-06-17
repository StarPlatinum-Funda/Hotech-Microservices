package com.github.hotech.inventory.application.queryservices;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import com.github.hotech.inventory.domain.model.queries.GetAllWarehouseQuery;
import com.github.hotech.inventory.domain.model.queries.GetWarehouseByIdQuery;
import com.github.hotech.inventory.domain.services.WarehouseQueryService;
import com.github.hotech.inventory.infrastructure.persistence.jpa.repositories.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseQueryServiceImpl implements WarehouseQueryService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseQueryServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public List<Warehouse> handle(GetAllWarehouseQuery query) {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> handle(GetWarehouseByIdQuery query) {
        return warehouseRepository.findById(query.warehouseId());
    }
}
