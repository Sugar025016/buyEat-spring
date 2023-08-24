package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.buy_eat.buy_eat.entity.Address;

public interface IAddressRepository extends JpaRepository<Address,Integer> {


    
}
