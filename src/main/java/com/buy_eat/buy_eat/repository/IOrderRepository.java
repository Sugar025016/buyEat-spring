package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Order;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Integer> {

	
}