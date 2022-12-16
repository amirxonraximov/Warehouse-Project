package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OutputRepository extends JpaRepository<Output, Integer> {

    @Query(nativeQuery = true, value = "select code from output order by code desc limit 1")
    Optional<Integer> getHighestCode();

    List<Output> getOutputByDate(Date date);
}
