package com.example.appwarehouse.payload;

import com.example.appwarehouse.entity.Warehouse;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String firstName;

    private String lastname;

    private String phoneNumber;

    private Integer code;

    private String password;

    private List<Integer> warehouseIDs;

}
