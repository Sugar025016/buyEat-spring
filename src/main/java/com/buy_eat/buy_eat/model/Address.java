package com.buy_eat.buy_eat.model;

import lombok.Data;

@Data
public class Address {
    private Integer id;

    private String city;

    private String area;

    private String detail;

    public Address() {

    }

}
