package com.buy_eat.buy_eat.model.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String account;

    private String name;
    
    private String email;

    private String phone;

    private List<ShopResponse> favoriteShops;

    private int cartCount;

    public UserResponse(User user) {
        BeanUtils.copyProperties(user,this);
        // this.nickname = user.getNickname();
        this.cartCount=user.getCarts().stream().mapToInt(v -> v.getQty()).sum();
        // this.account=user.getAccount();
        this.favoriteShops = user.getLoves().stream().map(v->new ShopResponse(v)).collect(Collectors.toList());
    }

}
