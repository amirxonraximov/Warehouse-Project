package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    boolean existsSupplierByPhoneNumber(String phoneNumber);

    List<Supplier> getSupplierByActive(Boolean active);
}
