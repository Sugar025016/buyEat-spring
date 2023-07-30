package com.buy_eat.buy_eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.model.response.ShopResponse;
import com.buy_eat.buy_eat.service.Impl.ShopService;


@RestController
@RequestMapping("/api")
public class ShopController {

    @Autowired
    ShopService shopService;


    @RequestMapping(path="/shops" , method = RequestMethod.GET )
    public List<Shop> getShops(){
        System.out.println("有鬼阿..........=.=+， 2");


        return shopService.findShops();
    }
    @RequestMapping(path="/shop" , method = RequestMethod.GET )
    public ResponseEntity<ShopResponse> getShop(@RequestParam int id){
        System.out.println("有鬼阿..........=.=+， 2");

        ShopResponse shopResponse = new ShopResponse(shopService.getShopById(id));
        return ResponseEntity.ok().body(shopResponse);
    }
}
