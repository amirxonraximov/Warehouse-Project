package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.InputProduct;
import com.example.appwarehouse.entity.WarningTime;
import com.example.appwarehouse.payload.InputProductResponse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.WarningTimeDto;
import com.example.appwarehouse.repository.InputProductRepository;
import com.example.appwarehouse.repository.InputRepository;
import com.example.appwarehouse.repository.WarningTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private WarningTimeRepository warningTimeRepository;
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private InputProductRepository inputProductRepository;

    public Result setWarningTime(WarningTimeDto warningTimeDto) {

        WarningTime warningTime = new WarningTime();
        warningTime.setDaysOfTime(warningTimeDto.getDaysOfTime());
        Optional<Integer> optionalWarningTime = warningTimeRepository.getWarningTime();
        if (optionalWarningTime.isPresent()) {
            warningTime.setId(optionalWarningTime.get());
        }
        warningTimeRepository.save(warningTime);
        return Result.success("Warning Time set!");
    }

    public Result getNotifications() {
        List<InputProductResponse> inputProductResponseList = new ArrayList<>();
        List<WarningTime> warningTimeList = warningTimeRepository.findAll();

        if (warningTimeList.isEmpty()) {
            return Result.success("Time has not been set", inputProductResponseList);
        }

        WarningTime warningTime = warningTimeList.get(0);
        Date today = Date.from(Instant.now());
        Date deltaDate = Date.from(Instant.now().plus(warningTime.getDaysOfTime(), ChronoUnit.DAYS));

        List<InputProduct> inputList = inputProductRepository.getInputProductsByExpireDateBetween(today, deltaDate);

        for (InputProduct input : inputList) {
            InputProductResponse inputProductResponse = new InputProductResponse();
            Optional<InputProduct> optionalInputProduct = inputProductRepository.getInputProductByInput_Id(input.getId());
            if (optionalInputProduct.isEmpty()) {
                continue;
            }
            inputProductResponse.setId(optionalInputProduct.get().getId());
            inputProductResponse.setName(optionalInputProduct.get().getProduct().getName());
            inputProductResponse.setAmount(optionalInputProduct.get().getAmount());
            inputProductResponse.setPrice(optionalInputProduct.get().getPrice());

            inputProductResponseList.add(inputProductResponse);
        }
        return Result.success(inputProductResponseList);
    }
}
