package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.OutputDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto) {

        Result result = outputService.addOutput(outputDto);
        return result;
    }

    @GetMapping
    public Result getOutputs() {

        Result result = outputService.getOutputs();
        return result;
    }

    @GetMapping("/{id}")
    public Result getOutputById(@PathVariable Integer id) {

        Result result = outputService.getById(id);
        return result;
    }
}
