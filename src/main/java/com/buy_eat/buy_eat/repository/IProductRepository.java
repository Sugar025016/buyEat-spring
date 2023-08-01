package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    // @Query(value = "select
    // com.xxxx.springsecuritydemo.model.response.ProductResponse(Product) from
    // Product")

    // long deleteBy(Integer id);

    Product getProductById(Integer id);
    

}