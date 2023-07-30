package com.buy_eat.buy_eat.model.response;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopResponse {

    private Integer id;

    private String name;
    private String phone;
    private String address;
    private String description;
    // private URL img;

    // private int qty;

    // private int total;

    // private int totalOriginPrice;

    public ShopResponse(Shop shop) {
        BeanUtils.copyProperties(shop,this);


    }

}
