package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// @Repository
// public class IOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.OrderDetail;
    
// }
@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

	
}