package com.buy_eat.buy_eat.service;

import java.util.Set;

import com.buy_eat.buy_eat.entity.Tab;
import com.buy_eat.buy_eat.model.request.TabProductRequest;

public interface ITabService {
    Set<Tab> findTabByShopId(int id);

    boolean setTabByShopId(int tabId, TabProductRequest tabProductRequest, int userId);

    boolean addTabByShopId( TabProductRequest tabProductRequest, int userId);
    boolean deleteTab(int tabId, int userId) ;
}
