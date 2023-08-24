package com.buy_eat.buy_eat.service;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.model.request.BackstageShopAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageShopPutRequest;
import com.buy_eat.buy_eat.model.request.ShopRequest;
import com.buy_eat.buy_eat.model.request.ShopSearchRequest;
import com.buy_eat.buy_eat.model.response.BackstageShopResponse;

public interface IShopService {
    // List<Shop> getShops();
    Set<Shop> findShops(ShopSearchRequest shopRequest);
    Shop getShopById(int id);
    Page<BackstageShopResponse> findShops(ShopSearchRequest shopRequest, Pageable pageable);
    boolean addShop(ShopRequest shopRequest);
    // boolean existsById(int id);
    boolean putShop(BackstageShopPutRequest shopPutRequest);
    boolean addShop(BackstageShopAddRequest ShopAddRequest);
    Set<Shop> findShopsLim();

}
