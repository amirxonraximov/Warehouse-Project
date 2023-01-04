package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.SupplierDto;
import com.example.appwarehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    public Result addSupplier(SupplierDto supplierDto) {

        boolean existsSupplierByPhoneNumber = supplierRepository.existsSupplierByPhoneNumber(supplierDto.getPhoneNumber());
        if (existsSupplierByPhoneNumber) {
            return Result.fail("Such a Supplier already exists in system!");
        }

        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());

        supplierRepository.save(supplier);
        return Result.success("Supplier added");
    }

    public Result getSuppliers() {

        List<Supplier> supplierList = supplierRepository.getSupplierByActive(true);
        return Result.success("Success", supplierList);
     }

    public Result updateSupplier(Integer id, SupplierDto supplierDto) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            return Result.fail("Supplier not found");
        }
        Supplier updatingSupplier = optionalSupplier.get();
        updatingSupplier.setName(supplierDto.getName());
        updatingSupplier.setPhoneNumber(supplierDto.getPhoneNumber());

        supplierRepository.save(updatingSupplier);
        return Result.success("Supplier edited");
    }

    public Result deleteSupplier(Integer id) {

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            return Result.fail("Supplier not found");
        }
        supplierRepository.deleteById(id);
        return Result.success("Supplier deleted");
    }
}
