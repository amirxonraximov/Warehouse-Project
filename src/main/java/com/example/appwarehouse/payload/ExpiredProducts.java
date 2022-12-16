package com.example.appwarehouse.payload;

import lombok.Data;

import java.util.List;

@Data
public class ExpiredProducts {

    List<InputProductResponse> productResponseList;

    int total;
}
