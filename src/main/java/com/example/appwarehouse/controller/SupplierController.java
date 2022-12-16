package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.SupplierDto;
import com.example.appwarehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping
    public Result addSupplier(@RequestBody SupplierDto supplierDto) {

        Result result = supplierService.addSupplier(supplierDto);
        return result;
    }

    @GetMapping
    public Result getSuppliers() {

        Result result = supplierService.getSuppliers();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateSupplier(@PathVariable Integer id, @RequestBody SupplierDto supplierDto) {

        Result result = supplierService.updateSupplier(id, supplierDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteSupplier(@PathVariable Integer id) {
        Result result = supplierService.deleteSupplier(id);
        return result;
    }
}
