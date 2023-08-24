package com.buy_eat.buy_eat.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.model.request.BackstageShopAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageShopPutRequest;
import com.buy_eat.buy_eat.model.request.ShopSearchRequest;
import com.buy_eat.buy_eat.model.response.BackstageShopResponse;
import com.buy_eat.buy_eat.model.response.ShopResponse;
import com.buy_eat.buy_eat.service.Impl.ShopService;

@RestController
@RequestMapping("/backstage/shop")
public class BackstageShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<BackstageShopResponse>> getShops(ShopSearchRequest shopRequest,
            @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<BackstageShopResponse> findShops = shopService.findShops(shopRequest, pageable);
        findShops.stream().forEach(v -> {
            v.setImgUrl( v.getImgUrl());
        });
        return ResponseEntity.ok().body(findShops);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ShopResponse> getShop(@PathVariable() int id) {

        ShopResponse shopResponse = new ShopResponse(shopService.getShopById(id));
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
