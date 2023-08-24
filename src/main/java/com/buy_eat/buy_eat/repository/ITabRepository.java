package com.buy_eat.buy_eat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Tab;

@Repository
public interface ITabRepository extends  JpaRepository<Tab,Integer>  {

    List<Tab> findAllByShopId(int id);
    
}
