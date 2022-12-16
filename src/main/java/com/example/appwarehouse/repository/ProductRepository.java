package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByNameAndCategoryId(String name, Integer category_id);

    List<Product> getProductByActive(Boolean active);

    @Query(nativeQuery = true, value = "select code from product order by code desc limit 1")
    Optional<Integer> getProductWithHighestCode();
}
