package com.buy_eat.buy_eat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Cart;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer>{
    
    List<Cart> getAllByUser_id(int id);

    Optional<Cart> findByIdAndUser_id(Integer id, int userId);

    Optional<Cart> getByIdAndUser_id(int cartId, int id);


    long countByUser_id(int id);

}
