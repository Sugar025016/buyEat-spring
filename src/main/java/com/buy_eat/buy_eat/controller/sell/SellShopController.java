package com.buy_eat.buy_eat.controller.sell;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.config.CustomUserDetails;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.model.response.SellShopResponse;
import com.buy_eat.buy_eat.model.response.ShopNameItemResponse;
import com.buy_eat.buy_eat.service.Impl.ShopService;

@RestController
@RequestMapping("/sell/shop")
public class SellShopController {

    @Autowired
    ShopService shopService;


    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<ShopNameItemResponse>> getShopsName(@AuthenticationPrincipal CustomUserDetails customUserDetails ) {
        List<Shop> findShops = shopService.getShopsByUserId(customUserDetails.getId());
        List<ShopNameItemResponse> collect = findShops.stream().map(v->new ShopNameItemResponse(v)).collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SellShopResponse> getShop(@PathVariable() int id,@AuthenticationPrincipal CustomUserDetails customUserDetails ) {
        SellShopResponse sellShopResponse = new SellShopResponse(shopService.getShopByUserId(customUserDetails.getId(), id));
        return ResponseEntity.ok().body(sellShopResponse);
    }



}
