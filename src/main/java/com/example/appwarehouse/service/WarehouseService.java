package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.CurrencyRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    public Result addWarehouse(Warehouse warehouse) {

        boolean existsByName = warehouseRepository.existsByName(warehouse.getName());

        if (existsByName) {
            return Result.fail("There is such a Warehouse");
        }
        warehouseRepository.save(warehouse);
        return Result.success("Warehouse added");
    }

    public Result getWarehouses() {
        List<Warehouse> warehouseList = warehouseRepository.getWarehouseByActive(true);
        return Result.success("Success", warehouseList);
    }

    public Result updateWarehouse(Integer id, Warehouse warehouse) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty()) {
            return Result.fail("Warehouse not found");
        }
        warehouse.setId(id);
        warehouseRepository.save(warehouse);
        return Result.success("Success", warehouse);
    }

    public Result deleteWarehouse(Integer id) {
        Optional<Warehouse> deletingWarehouse = warehouseRepository.findById(id);
        if (deletingWarehouse.isEmpty()) {
            return Result.fail("Warehouse not found");
        }
        warehouseRepository.deleteById(id);
        return Result.success("Warehouse deleted");
    }
}
