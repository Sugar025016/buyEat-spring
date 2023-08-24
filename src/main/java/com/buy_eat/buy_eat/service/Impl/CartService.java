package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.buy_eat.buy_eat.entity.Cart;
import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.CartRequest;
import com.buy_eat.buy_eat.model.response.ShopCartResponse;
import com.buy_eat.buy_eat.repository.ICartRepository;
import com.buy_eat.buy_eat.repository.IProductRepository;
import com.buy_eat.buy_eat.repository.IUserRepository;
import com.buy_eat.buy_eat.service.ICartService;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository iCartRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IProductRepository iProductRepository;

    @Override
    public ShopCartResponse getAllByUserId(int id) {
        List<Cart> allByUser_id = iCartRepository.getAllByUser_id(id);
        ShopCartResponse shopCartResponse = new ShopCartResponse(allByUser_id);
        return shopCartResponse;
    }

    @Transactional
    @Override
    public int addCart(int id, CartRequest cartRequest) {

        User userOptional = iUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Product product = iProductRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        this.getClass().getName() + "Product not found"));

        List<Cart> allByUser_id = iCartRepository.getAllByUser_id(id);
        
        boolean anyMatch = allByUser_id.stream().anyMatch(v -> v.getShopId() != product.getTab().getShop().getId());
        if (anyMatch) {
            iCartRepository.deleteAll(allByUser_id);
            iCartRepository.flush();
        }

        Cart cart = new Cart(cartRequest, userOptional, product);

        iCartRepository.save(cart);


        return iCartRepository.getAllByUser_id(id).stream().mapToInt(v->v.getQty()).sum();

    }

    @Override
    public ShopCartResponse putCart(int userId, int cartId, CartRequest cartRequest) {

        Optional<Cart> cart = iCartRepository.findByIdAndUser_id(cartId, userId);

        Cart orElseThrow = cart.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        orElseThrow.setCart(cartRequest);

        iCartRepository.save(orElseThrow);

        return getAllByUserId(userId);
    }

    @Override
    public ShopCartResponse deleteCart(int cartId, int userId) {
        Optional<Cart> cart = iCartRepository.getByIdAndUser_id(cartId, userId);

        Cart orElseThrow = cart.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        iCartRepository.delete(orElseThrow);
        return getAllByUserId(userId);

    }

    @Override
    public ShopCartResponse putCart(int userId, int cartId, int qty) {

        Optional<Cart> cart = iCartRepository.findByIdAndUser_id(cartId, userId);

        Cart orElseThrow = cart.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        orElseThrow.setQty(qty);
        iCartRepository.save(orElseThrow);

        return getAllByUserId(userId);

    }

}
