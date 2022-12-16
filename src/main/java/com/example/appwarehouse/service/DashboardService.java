package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.entity.InputProduct;
import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.entity.OutputProduct;
import com.example.appwarehouse.payload.*;
import com.example.appwarehouse.repository.InputProductRepository;
import com.example.appwarehouse.repository.InputRepository;
import com.example.appwarehouse.repository.OutputProductRepository;
import com.example.appwarehouse.repository.OutputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class DashboardService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Result getDailyInputProducts(String date) {
        try {
            DailyInputProducts dailyInputProducts = new DailyInputProducts();
            List<InputProductResponse> inputProductResponseList = new ArrayList<>();

            List<Input> inputList = inputRepository.getInputByDate(dateFormat.parse(date));

            double sum = 0.0;
            for (Input input : inputList) {
                InputProductResponse inputProductResponse = new InputProductResponse();
                Optional<InputProduct> optionalInputProduct = inputProductRepository.getInputProductByInput_Id(input.getId());
                if (optionalInputProduct.isEmpty()) {
                    continue;
                }
                inputProductResponse.setId(optionalInputProduct.get().getId());
                inputProductResponse.setName(optionalInputProduct.get().getProduct().getName());
                inputProductResponse.setAmount(optionalInputProduct.get().getAmount());
                inputProductResponse.setPrice(optionalInputProduct.get().getPrice());

                sum += inputProductResponse.getPrice() * inputProductResponse.getAmount();

                inputProductResponseList.add(inputProductResponse);
            }

            dailyInputProducts.setProducts(inputProductResponseList);
            dailyInputProducts.setTotalSum(sum);

            return Result.success(dailyInputProducts);
        } catch (ParseException e) {
            return Result.fail("Invalid date format: " + date);
        }
    }

    public Result getDailyMostOutputProducts(String date) {
        try {
            List<OutputProductResponse> outputProductResponseList = new ArrayList<>();

            List<Output> outputList = outputRepository.getOutputByDate(dateFormat.parse(date));

            for (Output output : outputList) {
                OutputProductResponse outputProductResponse = new OutputProductResponse();
                Optional<OutputProduct> optionalOutputProduct = outputProductRepository.getOutputProductByOutput_Id(output.getId());
                if (optionalOutputProduct.isEmpty()) {
                    continue;
                }
                outputProductResponse.setId(optionalOutputProduct.get().getId());
                outputProductResponse.setName(optionalOutputProduct.get().getProduct().getName());
                outputProductResponse.setAmount(optionalOutputProduct.get().getAmount());
                outputProductResponse.setPrice(optionalOutputProduct.get().getPrice());

                outputProductResponseList.add(outputProductResponse);
            }

            Collections.sort(outputProductResponseList, (a, b) -> a.getPrice() * a.getAmount() >= b.getAmount() * b.getPrice() ? 1 : -1);

            return Result.success(outputProductResponseList);

        } catch (ParseException e) {
            return Result.fail("Invalid date format: " + date);
        }
    }

    public Result getExpiredProducts() {
        ExpiredProducts expiredProducts = new ExpiredProducts();
        List<InputProductResponse> inputProductResponseList = new ArrayList<>();

        List<Input> inputList = inputRepository.getInputByDate(Date.from(Instant.now()));

        int count = 0;
        for (Input input : inputList) {
            InputProductResponse inputProductResponse = new InputProductResponse();
            Optional<InputProduct> optionalInputProduct = inputProductRepository.getInputProductByInput_Id(input.getId());
            if (optionalInputProduct.isEmpty()) {
                continue;
            }
            inputProductResponse.setId(optionalInputProduct.get().getId());
            inputProductResponse.setName(optionalInputProduct.get().getProduct().getName());
            inputProductResponse.setAmount(optionalInputProduct.get().getAmount());
            inputProductResponse.setPrice(optionalInputProduct.get().getPrice());

            count++;

            inputProductResponseList.add(inputProductResponse);
        }

        expiredProducts.setProductResponseList(inputProductResponseList);
        expiredProducts.setTotal(count);

        return Result.success(expiredProducts);

    }
}

