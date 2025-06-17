package com.github.hotech.inventory.infrastructure.persistence.jpa.repositories;

import com.github.hotech.inventory.domain.model.aggregates.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findAllByBrandName(String brand);
    boolean existsByProductTitle(String itemName);

    boolean existsByProductTitleAndIdNot(String productTitle, Long id);
}
