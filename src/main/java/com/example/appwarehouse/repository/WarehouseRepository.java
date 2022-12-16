package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);

    List<Warehouse> getWarehouseByActive(Boolean active);
}
