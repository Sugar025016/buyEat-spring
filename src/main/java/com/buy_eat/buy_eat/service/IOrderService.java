package com.buy_eat.buy_eat.service;


import com.buy_eat.buy_eat.model.request.OrderRequest;

public interface IOrderService {
    boolean addOrder(int userId,OrderRequest orderRequest);

}
