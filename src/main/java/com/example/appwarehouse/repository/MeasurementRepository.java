package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);

    List<Measurement> getMeasurementByActive(Boolean active);

}
