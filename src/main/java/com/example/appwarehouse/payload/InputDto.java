package com.example.appwarehouse.payload;


import lombok.Data;

@Data
public class InputDto {

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private Integer productId;

    private Double amount;

    private Double price;
}
