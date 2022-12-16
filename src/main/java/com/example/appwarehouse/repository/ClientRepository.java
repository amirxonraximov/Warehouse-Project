package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.Client;
import com.example.appwarehouse.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsClientByPhoneNumber(String phoneNumber);

    List<Client> getClientByActive(Boolean active);
}
