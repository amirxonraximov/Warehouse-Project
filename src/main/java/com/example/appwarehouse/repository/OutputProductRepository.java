package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.InputProduct;
import com.example.appwarehouse.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {

    Optional<OutputProduct> getOutputProductByOutput_Id(Integer id);
}
