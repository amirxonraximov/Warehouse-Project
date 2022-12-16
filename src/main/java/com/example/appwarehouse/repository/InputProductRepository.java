package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.InputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct, Integer> {

    Optional<InputProduct> getInputProductByInput_Id(Integer id);

    List<InputProduct> getInputProductsByExpireDateBetween(Date today, Date date);

}
