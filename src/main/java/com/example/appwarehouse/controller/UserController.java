package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.UserDto;
import com.example.appwarehouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto) {
        Result result = userService.addUser(userDto);
        return result;
    }

    @GetMapping
    public Result getUsers() {

        Result result = userService.getUsers();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {

        Result result = userService.updateUser(id, userDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id) {

        Result result = userService.deleteUser(id);
        return result;
    }
}
