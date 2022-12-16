package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.WarningTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WarningTimeRepository extends JpaRepository<WarningTime, Integer> {

    @Query(nativeQuery = true, value = "select id from warning_time limit 1")
    Optional<Integer> getWarningTime();

}
