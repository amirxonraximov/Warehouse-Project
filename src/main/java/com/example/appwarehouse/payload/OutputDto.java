package com.example.appwarehouse.payload;

import lombok.Data;


@Data
public class OutputDto {

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private Integer productId;

    private Double amount;

    private Double price;
}
