package com.buy_eat.buy_eat.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.buy_eat.buy_eat.entity.Product;

public interface IProductService {
    List<Product> findAll();
    Product getProductById(int id);
    
    Product deleteProductById(int id) throws NotFoundException;
    // Product putProductById(int id);
}
