package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByName(String name);

    List<Currency> getCurrencyByActive(Boolean active);


}
