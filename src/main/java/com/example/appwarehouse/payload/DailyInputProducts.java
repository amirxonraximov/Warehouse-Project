package com.example.appwarehouse.payload;

import lombok.Data;

import java.util.List;

@Data
public class DailyInputProducts {
    List<InputProductResponse> products;
    Double totalSum;
}

