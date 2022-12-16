package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.CurrencyRepository;
import com.example.appwarehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {


    @Autowired
    CurrencyService currencyService;
    @Autowired
    private CurrencyRepository currencyRepository;

    @PostMapping
    public Result addCurrencycontroller(@RequestBody Currency currency) {

        Result result = currencyService.addCurrency(currency);
        return result;
    }

    @GetMapping
    public Result getCurrencies() {
        Result result = currencyService.getCurrencies();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateCurrency(@PathVariable Integer id, @RequestBody Currency currency) {

        Result result = currencyService.updateCurrency(id, currency);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteCurrency(@PathVariable Integer id) {

        Result result = currencyService.deleteCurrency(id);
        return result;
    }
}
