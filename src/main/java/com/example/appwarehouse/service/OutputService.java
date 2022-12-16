package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.*;
import com.example.appwarehouse.payload.OutputDto;
import com.example.appwarehouse.payload.OutputResponse;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    ProductRepository productRepository;

    public Result addOutput(OutputDto outputDto) {

        Output output = new Output();

        output.setDate(Date.from(Instant.now()));

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            return Result.fail("Warehouse not found");
        }
        output.setWarehouse(optionalWarehouse.get());

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()) {
            return Result.fail("Client not found");
        }
        output.setClient(optionalClient.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            return Result.fail("Currency not found");
        }
        output.setCurrency(optionalCurrency.get());

        Optional<Integer> highestCode = outputRepository.getHighestCode();

        if (highestCode.isEmpty())
            output.setCode(1);
        else
            output.setCode(highestCode.get() + 1);

        output.setFactureNumber(String.valueOf(System.currentTimeMillis()));

        Output savedOutput = outputRepository.save(output);

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setOutput(savedOutput);

        Optional<Product> optionalProduct = productRepository.findById(outputDto.getProductId());
        if (optionalProduct.isEmpty()) {
            return Result.fail("Product not found");
        }
        outputProduct.setProduct(optionalProduct.get());

        outputProduct.setAmount(outputDto.getAmount());
        outputProduct.setPrice(outputDto.getPrice());

        outputProductRepository.save(outputProduct);

        return Result.success("Output created");
    }

    public Result getOutputs() {

        List<OutputResponse> outputResponseList = new ArrayList<>();

        List<Output> outputs = outputRepository.findAll();

        for (Output output : outputs) {
            OutputResponse outputResponse = new OutputResponse();
            outputResponse.setId(output.getId());
            outputResponse.setCurrency(output.getCurrency());
            outputResponse.setClient(output.getClient());
            outputResponse.setWarehouse(output.getWarehouse());
            outputResponse.setDate(output.getDate());
            outputResponse.setCode(output.getCode());
            outputResponse.setFactureNumber(output.getFactureNumber());

            Optional<OutputProduct> optionalOutputProduct = outputProductRepository.getOutputProductByOutput_Id(output.getId());
            if (optionalOutputProduct.isEmpty()) {
                continue;
            }
            outputResponse.setOutputProduct(optionalOutputProduct.get());
            outputResponseList.add(outputResponse);
        }

        return Result.success(outputResponseList);
    }

    public Result getById(Integer id) {
        OutputResponse outputResponse = new OutputResponse();

        Optional<Output> optionalOutput = outputRepository.findById(id);
        if (optionalOutput.isEmpty()) {
            return Result.fail("Not found");
        }

        outputResponse.setId(optionalOutput.get().getId());
        outputResponse.setCurrency(optionalOutput.get().getCurrency());
        outputResponse.setClient(optionalOutput.get().getClient());
        outputResponse.setWarehouse(optionalOutput.get().getWarehouse());
        outputResponse.setDate(optionalOutput.get().getDate());
        outputResponse.setCode(optionalOutput.get().getCode());
        outputResponse.setFactureNumber(optionalOutput.get().getFactureNumber());

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.getOutputProductByOutput_Id(optionalOutput.get().getId());
        if (optionalOutputProduct.isEmpty()) {
            return Result.fail("Not found");
        }
        outputResponse.setOutputProduct(optionalOutputProduct.get());

        return Result.success(outputResponse);
    }

}
