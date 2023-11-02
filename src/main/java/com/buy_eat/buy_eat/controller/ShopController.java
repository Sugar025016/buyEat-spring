package com.buy_eat.buy_eat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.model.request.BackstageShopAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageShopPutRequest;
import com.buy_eat.buy_eat.model.request.ShopSearchRequest;
import com.buy_eat.buy_eat.model.response.ShopDetailResponse;
import com.buy_eat.buy_eat.model.response.ShopResponse;
import com.buy_eat.buy_eat.service.Impl.ShopService;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<ShopResponse>> getShops(ShopSearchRequest shopRequest) {

        Set<Shop> findShops = shopService.findShops(shopRequest);
        List<ShopResponse> arrayList = new ArrayList<>();
        for (Shop findShop : findShops) {
            ShopResponse shopResponse = new ShopResponse(
                findShop);
            arrayList.add( shopResponse);
        }
        return ResponseEntity.ok().body(arrayList);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShopDetailResponse> getShop(@PathVariable() int id) {

        ShopDetailResponse shopResponse = new ShopDetailResponse(shopService.getShopById(id));
        return ResponseEntity.ok().body(shopResponse);
    }


    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> putShop(@RequestBody  BackstageShopPutRequest shopPutRequest ) {
        return ResponseEntity.ok().body(shopService.putShop(shopPutRequest));
    }


    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> postShop(@RequestBody  BackstageShopAddRequest shopPutRequest ) {
        return ResponseEntity.ok().body(shopService.addShop(shopPutRequest));
    }


}
