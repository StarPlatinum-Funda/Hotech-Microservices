package com.github.hotech.inventory.infrastructure.persistence.jpa.repositories;

import com.github.hotech.inventory.domain.model.aggregates.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
