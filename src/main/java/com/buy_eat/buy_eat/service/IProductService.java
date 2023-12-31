package com.buy_eat.buy_eat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.model.request.BackstageProductAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageProductPutRequest;
import com.buy_eat.buy_eat.model.request.SellProductRequest;
import com.buy_eat.buy_eat.model.response.BackstageProductResponse;

public interface IProductService {
    List<Product> findAll();

    Product getProductById(int id);

    List<Product> getProductsByShopId(int shopId);

    // Product deleteProductById(int id) throws NotFoundException;

    // Product putProductById(int id);
    Page<BackstageProductResponse> getByPage(Integer shopId, Pageable pageable);

    boolean putShop(BackstageProductPutRequest productPutRequest);

    boolean addProduct(BackstageProductAddRequest productAddRequest);

    boolean deleteProductById(int productId, int userId);

    boolean setOrderable(int productId, boolean isOrderable, int userId);


    List<Product> getProductsByShopIdAndUserID(int productId, int userId);

    boolean putSellProduct(SellProductRequest sellProductRequest,int productId,int userId) ;

    boolean addSellProduct(SellProductRequest sellProductRequest,int shopId, int userId) ;
}
