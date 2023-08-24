package com.buy_eat.buy_eat.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.model.response.BackstageProductResponse;

@Repository
public interface IProductRepository extends JpaRepository<Product, Integer> {

    Product getProductById(Integer id);

    // List<Product> getProductByShop_id(Integer id);

    List<Product> getProductByTab_shop_id(Integer id);

    // List<Product> getAllByShop_Id(Integer id);
    List<Product> getAllByTab_shop_id(Integer id);

    @Query(value = "SELECT NEW com.buy_eat.buy_eat.model.response.BackstageProductResponse(p) " +
            "FROM Product p LEFT JOIN p.tab t " +
            "LEFT JOIN t.shop s " +
            "WHERE (:shop_id IS NULL OR s.id = :shop_id)", countQuery = "SELECT COUNT(p)" +
                    "FROM Product p LEFT JOIN p.tab t " +
                    "LEFT JOIN t.shop s " +
                    "WHERE (:shop_id IS NULL OR s.id = :shop_id)")
    Page<BackstageProductResponse> findAllByShopId(@Param("shop_id") Integer shopId, Pageable pageable);

}