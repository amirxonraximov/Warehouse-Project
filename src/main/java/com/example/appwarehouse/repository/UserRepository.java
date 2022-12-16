package com.example.appwarehouse.repository;

import com.example.appwarehouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsUserByPhoneNumber(String phoneNumber);

    List<User> getUserByActive(Boolean active);

    @Query(nativeQuery = true, value = "select code from users order by code desc limit 1")
    Optional<Integer> getUserWithHighestCode();
}
