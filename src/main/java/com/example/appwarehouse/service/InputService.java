package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.*;
import com.example.appwarehouse.payload.InputDto;
import com.example.appwarehouse.payload.InputResponse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ProductRepository productRepository;

    public Result addInput(InputDto inputDto) {

        Input input = new Input();

        input.setDate(Timestamp.from(Instant.now()));

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return Result.fail("Warehouse not found");
        }
        input.setWarehouse(optionalWarehouse.get());

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            return Result.fail("Supplier not found");
        }
        input.setSupplier(optionalSupplier.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return Result.fail("Currency not found");
        }
        input.setCurrency(optionalCurrency.get());

        Optional<Integer> highestCode = inputRepository.getHighestCode();

        if (highestCode.isEmpty())
            input.setCode(1);
        else
            input.setCode(highestCode.get() + 1);

        input.setFactureNumber(String.valueOf(System.currentTimeMillis()));

        Input savedInput = inputRepository.save(input);


        InputProduct inputProduct = new InputProduct();

        inputProduct.setInput(savedInput);

        Optional<Product> optionalProduct = productRepository.findById(inputDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return Result.fail("Product not found");
        }
        inputProduct.setProduct(optionalProduct.get());

        inputProduct.setAmount(inputDto.getAmount());
        inputProduct.setPrice(inputDto.getPrice());

        inputProductRepository.save(inputProduct);

        return Result.success("Input created");
    }

    public Result getInputs() {

        List<InputResponse> inputResponseList = new ArrayList<>();

        List<Input> inputs = inputRepository.findAll();

        for (Input input : inputs) {
            InputResponse inputResponse = new InputResponse();
            inputResponse.setId(input.getId());
            inputResponse.setCurrency(input.getCurrency());
            inputResponse.setSupplier(input.getSupplier());
            inputResponse.setWarehouse(input.getWarehouse());
            inputResponse.setDate(input.getDate());
            inputResponse.setCode(input.getCode());
            inputResponse.setFactureNumber(input.getFactureNumber());

            Optional<InputProduct> optionalInputProduct = inputProductRepository.getInputProductByInput_Id(input.getId());
            if (optionalInputProduct.isEmpty()) {
                continue;
            }
            inputResponse.setInputProduct(optionalInputProduct.get());
            inputResponseList.add(inputResponse);
        }

        return Result.success(inputResponseList);
    }

    public Result getById(Integer id) {
        InputResponse inputResponse = new InputResponse();

        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isEmpty()) {
            return Result.fail("Not found");
        }

        inputResponse.setId(optionalInput.get().getId());
        inputResponse.setCurrency(optionalInput.get().getCurrency());
        inputResponse.setSupplier(optionalInput.get().getSupplier());
        inputResponse.setWarehouse(optionalInput.get().getWarehouse());
        inputResponse.setDate(optionalInput.get().getDate());
        inputResponse.setCode(optionalInput.get().getCode());
        inputResponse.setFactureNumber(optionalInput.get().getFactureNumber());

        Optional<InputProduct> optionalInputProduct = inputProductRepository.getInputProductByInput_Id(optionalInput.get().getId());
        if (optionalInputProduct.isEmpty()) {
            return Result.fail("Not found");
        }
        inputResponse.setInputProduct(optionalInputProduct.get());

        return Result.success(inputResponse);
    }

}
