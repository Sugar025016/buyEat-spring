package com.buy_eat.buy_eat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.config.CustomUserDetails;
import com.buy_eat.buy_eat.model.request.CartRequest;
import com.buy_eat.buy_eat.model.response.ShopCartResponse;
import com.buy_eat.buy_eat.service.Impl.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<ShopCartResponse> get(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok().body(cartService.getAllByUserId(customUserDetails.getId()));
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Integer> post(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody CartRequest cartRequest) {
        return ResponseEntity.ok().body(cartService.addCart(customUserDetails.getId(), cartRequest));
    }

    // @RequestMapping(path = "/{cartId}", method = RequestMethod.PUT)
    // public ResponseEntity<ShopCartResponse> put(@PathVariable int cartId,@RequestBody CartRequest cartRequest,
    //         @AuthenticationPrincipal CustomUserDetails customUserDetails) {
    //     ShopCartResponse putCart = cartService.putCart(customUserDetails.getId(),  cartId, cartRequest);
    //     return ResponseEntity.ok().body(putCart);
    // }

    @RequestMapping(path = "/{cartId}/{qty}", method = RequestMethod.PUT)
    public ResponseEntity<ShopCartResponse> put(@PathVariable int cartId,@PathVariable int qty,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ShopCartResponse putCart = cartService.putCart(customUserDetails.getId(),  cartId, qty);
        return ResponseEntity.ok().body(putCart);
    }

    @RequestMapping(path = "/{cartId}", method = RequestMethod.DELETE)
    public ResponseEntity<ShopCartResponse> delete(@PathVariable int cartId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        ShopCartResponse deleteCart = cartService.deleteCart(cartId, customUserDetails.getId());
        return ResponseEntity.ok().body(deleteCart);
    }

}
