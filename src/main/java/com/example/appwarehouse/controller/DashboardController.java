package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    DashboardService dashboardService;

    @GetMapping("/input/{date}")
    public Result getDailyInputProducts(@PathVariable String date) {
        Result result = dashboardService.getDailyInputProducts(date);
        return result;
    }

    @GetMapping("/most-output/{date}")
    public Result getDailyMostOutputProducts(@PathVariable String date) {

        Result result = dashboardService.getDailyMostOutputProducts(date);
        return result;
    }

    @GetMapping("/expiring-products")
    public Result getExpiredProducts() {
        Result result = dashboardService.getExpiredProducts();
        return result;
    }
}
