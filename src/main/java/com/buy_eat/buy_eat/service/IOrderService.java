package com.buy_eat.buy_eat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.buy_eat.buy_eat.model.request.OrderRequest;
import com.buy_eat.buy_eat.model.response.OrderFinishResponse;
import com.buy_eat.buy_eat.model.response.OrderResponse;
public interface IOrderService {
    
    boolean addOrder(int userId, OrderRequest orderRequest);

    Page<OrderResponse> getOrder(int userId,Pageable pageable);

    Page<OrderFinishResponse> getOrderByShop(int userId,int shopId,List<Integer> keyByClassify,Pageable pageable);

    boolean putOrderByShop(int userId,int shopId,int status,List<Integer> keyByClassify);
}
