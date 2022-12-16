package com.example.appwarehouse.payload;

import lombok.Data;

@Data
public class InputProductResponse {
    private Integer id;

    private String name;

    private Double amount;

    private Double price;
}
