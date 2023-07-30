package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Shop;

@Repository
public interface IShopRepository extends JpaRepository<Shop, Integer> {

    // @Override
    // Optional<Shop> findById(Integer id);

    
    Shop getShopById(Integer id);




}