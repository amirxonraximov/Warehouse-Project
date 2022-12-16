package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.payload.WarningTimeDto;
import com.example.appwarehouse.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public Result setWarningTime(@RequestBody WarningTimeDto warningTimeDto) {

        Result result = notificationService.setWarningTime(warningTimeDto);
        return result;
    }

    @GetMapping
    public Result getNotifications() {

        Result result = notificationService.getNotifications();
        return result;

    }

}


