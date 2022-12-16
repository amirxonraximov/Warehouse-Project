package com.example.appwarehouse.payload;

import com.example.appwarehouse.entity.*;
import lombok.Data;

import java.util.Date;

@Data
public class OutputResponse {

    private Integer id;

    private Date date;

    private Warehouse warehouse;

    private Client client;

    private Currency currency;

    private String factureNumber;

    private OutputProduct outputProduct;

    private Integer code;
}
