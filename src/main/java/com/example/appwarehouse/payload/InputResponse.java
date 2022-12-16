package com.example.appwarehouse.payload;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.entity.InputProduct;
import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.entity.Warehouse;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class InputResponse {
    private Integer id;

    private Date date;

    private Warehouse warehouse;

    private Supplier supplier;

    private Currency currency;

    private String factureNumber;

    private InputProduct inputProduct;

    private Integer code;
}
