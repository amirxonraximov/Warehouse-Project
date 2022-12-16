package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency) {

        boolean existsByName = currencyRepository.existsByName(currency.getName());

        if (existsByName) {
            return Result.fail("Currency not found");
        }
        currencyRepository.save(currency);
        return Result.success("MCurrency added");
    }

    public Result getCurrencies() {
        List<Currency> currencyList = currencyRepository.getCurrencyByActive(true);
        return Result.success("Success", currencyList);
    }

    public Result updateCurrency(Integer id, Currency currency) {
        Optional<Currency> oldCurrency = currencyRepository.findById(id);

        if (oldCurrency.isEmpty()) {
            return Result.fail("Currency not found");
        }

        currency.setId(id);
        currencyRepository.save(currency);
        return Result.success("Success", currency);

    }

    public Result deleteCurrency(Integer id) {
        Optional<Currency> deletingCurrency = currencyRepository.findById(id);
        if (deletingCurrency.isEmpty()) {
            return Result.fail("Currency not found");
        }
        currencyRepository.deleteById(id);
        return Result.success("Currency deleted");

    }
}
