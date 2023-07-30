package com.buy_eat.buy_eat.service;

import java.util.List;

import com.buy_eat.buy_eat.entity.Product;

public interface IProductService {
    List<Product> findAll();
    Product getProductById(int id);
}
