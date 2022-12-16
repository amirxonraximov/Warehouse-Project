package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.InputDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto) {
        Result result = inputService.addInput(inputDto);
        return result;
    }

    @GetMapping
    public Result getInputs() {

        Result result = inputService.getInputs();
        return result;
    }

    @GetMapping("/{id}")
    public Result getInputById(@PathVariable Integer id) {

        Result result = inputService.getById(id);
        return result;
    }
}
