package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.repository.IProductRepository;
import com.buy_eat.buy_eat.service.IProductService;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository iProductRepository;
    @Override
    public List<Product> findAll() {
        // TODO Auto-generated method stub
        return iProductRepository.findAll();
    }

 
    
    @Override
    public Product getProductById(int id) {
        Optional<Product> findById = iProductRepository.findById(id);
        if(!findById.isPresent()){
            return null;
        }
        return  findById.get();
        
    }
    
    
}
