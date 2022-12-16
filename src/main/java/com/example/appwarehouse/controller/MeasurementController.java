package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        Result result = measurementService.addMeasurement(measurement);
        return result;
    }


    @GetMapping
    public Result getMeasurements() {
        Result result = measurementService.getMeasurements();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement) {

        Result result = measurementService.updateMeasurement(id, measurement);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteMeasurement(@PathVariable Integer id) {

        Result result = measurementService.deleteMeasurement(id);
        return result;
    }
}
