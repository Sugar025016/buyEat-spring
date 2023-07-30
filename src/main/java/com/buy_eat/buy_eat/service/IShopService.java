package com.buy_eat.buy_eat.service;

import java.util.List;

import com.buy_eat.buy_eat.entity.Shop;

public interface IShopService {
    // List<Shop> getShops();
    List<Shop> findShops();
    Shop getShopById(int id);
}
