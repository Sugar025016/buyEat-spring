package com.buy_eat.buy_eat.service;

import com.buy_eat.buy_eat.model.request.CartRequest;
import com.buy_eat.buy_eat.model.response.ShopCartResponse;

public interface ICartService {

    ShopCartResponse getAllByUserId(int userId);

    int addCart(int userId,CartRequest cartRequest);


    ShopCartResponse putCart(int userId, int cartId,CartRequest cartRequest);

    ShopCartResponse putCart(int userId, int cartId,int qty);

    ShopCartResponse deleteCart(int userId,int cartId);
    
}
