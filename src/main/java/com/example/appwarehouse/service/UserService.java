package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.User;
import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.UserDto;
import com.example.appwarehouse.repository.UserRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Result addUser(UserDto userDto) {

        boolean existsUserByPhoneNumber = userRepository.existsUserByPhoneNumber(userDto.getPhoneNumber());
        if (existsUserByPhoneNumber) {
            return Result.fail("Such a User already exists in system!");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastname());
        user.setPhoneNumber(userDto.getPhoneNumber());

        Optional<Integer> highestCode = userRepository.getUserWithHighestCode();

        if (highestCode.isEmpty())
            user.setCode(1);
        else
            user.setCode(highestCode.get() + 1);

        user.setPassword(userDto.getPassword());

        List<Warehouse> userWarehouses = new ArrayList<>();

        for (Integer warehouseID : userDto.getWarehouseIDs()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseID);
            if (optionalWarehouse.isEmpty()) {
                return Result.fail(String.format("Warehouse with this id not found: %s", warehouseID));
            }
            userWarehouses.add(optionalWarehouse.get());
        }

        user.setWarehouses(new HashSet<>(userWarehouses));

        userRepository.save(user);
        return Result.success("User added");
    }

    public Result getUsers() {
        List<User> userList = userRepository.getUserByActive(true);
        return Result.success("Success", userList);
    }

    public Result updateUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return Result.fail("User not found");
        }
        User updatingUser = optionalUser.get();
        updatingUser.setFirstName(userDto.getFirstName());
        updatingUser.setLastName(userDto.getLastname());
        updatingUser.setPhoneNumber(userDto.getPhoneNumber());

        Optional<Integer> highestCode = userRepository.getUserWithHighestCode();

        if (highestCode.isEmpty())
            updatingUser.setCode(1);
        else
            updatingUser.setCode(highestCode.get() + 1);

        updatingUser.setPassword(userDto.getPassword());

        List<Warehouse> userWarehouses = new ArrayList<>();

        for (Integer warehouseID : userDto.getWarehouseIDs()) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseID);
            if (optionalWarehouse.isEmpty()) {
                return Result.fail(String.format("Warehouse with this id not found: %s", warehouseID));
            }
            userWarehouses.add(optionalWarehouse.get());
        }

        updatingUser.setWarehouses(new HashSet<>(userWarehouses));

        userRepository.save(updatingUser);
        return Result.success("User edited");
    }

    public Result deleteUser(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return Result.fail("User not found");
        }
        userRepository.deleteById(id);
        return Result.success("User deleted");
    }
}
