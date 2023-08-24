package com.buy_eat.buy_eat.service;

import java.util.List;

import com.buy_eat.buy_eat.entity.Tab;

public interface ITabService {
    List<Tab> findTabByShopId(int id);
}
