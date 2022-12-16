package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.WarehouseRepository;
import com.example.appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse) {

        Result result = warehouseService.addWarehouse(warehouse);
        return result;
    }

    @GetMapping
    public Result getWarehouses() {
        Result result = warehouseService.getWarehouses();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse) {

        Result result = warehouseService.updateWarehouse(id, warehouse);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id) {

        Result result = warehouseService.deleteWarehouse(id);
        return result;
    }
}
