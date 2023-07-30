package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.repository.IShopRepository;
import com.buy_eat.buy_eat.service.IShopService;

@Service
public class ShopService implements IShopService {

    @Autowired
    IShopRepository iShopRepository;
    @Override
    public List<Shop> findShops() {
        return iShopRepository.findAll();
    }
    @Override
    public Shop getShopById(int id) {

        Optional<Shop> findById = iShopRepository.findById(id);
        if(!findById.isPresent()){
            return null;
        }
        return  findById.get();
    }
    
}
