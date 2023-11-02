package com.buy_eat.buy_eat.model.response;

import com.buy_eat.buy_eat.entity.Shop;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopNameItemResponse {

    private Integer id;

    private String name;

    public ShopNameItemResponse(Shop shop ) {
        this.id = shop.getId();
        this.name = shop.getName();
    }



    
}
