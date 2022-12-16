package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement) {

        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {
            return Result.fail("There is such a unit of measurement");
        }
        measurementRepository.save(measurement);
        return Result.fail("Measurement added");
    }

    public Result getMeasurements() {
        List<Measurement> measurementList = measurementRepository.getMeasurementByActive(true);
        return Result.success("Success", measurementList);
    }

    public Result updateMeasurement(Integer id, Measurement measurement) {
        Optional<Measurement> oldMeasurement = measurementRepository.findById(id);

        if (oldMeasurement.isEmpty()) {
            return Result.fail("Measurement not found");
        }

        measurement.setId(id);
        measurementRepository.save(measurement);
        return Result.success("Success", measurement);

    }

    public Result deleteMeasurement(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isEmpty()) {
            return Result.fail("Measurement not found");
        }
        measurementRepository.deleteById(id);
        return Result.success("Measurement deleted");

    }

}
