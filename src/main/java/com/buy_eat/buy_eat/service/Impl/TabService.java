package com.buy_eat.buy_eat.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Tab;
import com.buy_eat.buy_eat.repository.ITabRepository;
import com.buy_eat.buy_eat.service.ITabService;

@Service
public class TabService implements ITabService {
    @Autowired
    ITabRepository iTabRepository;

    @Override
    public List<Tab> findTabByShopId(int id) {
        return iTabRepository.findAllByShopId(id);
    }
    
}
