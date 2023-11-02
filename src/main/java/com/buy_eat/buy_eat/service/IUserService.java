package com.buy_eat.buy_eat.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.PasswordRequest;
import com.buy_eat.buy_eat.model.request.UserPutRequest;
import com.buy_eat.buy_eat.model.request.UserRequest;
import com.buy_eat.buy_eat.model.response.BackstageUserResponse;

public interface IUserService {
    User findById(int id);
    User findByAccount(String account);
    Page<BackstageUserResponse> findByName(Pageable pageable, String name);
    boolean addUser(UserRequest userRequest);
    boolean existByAccount(String account);
    boolean putUser(UserPutRequest userPutRequest, int id);
    boolean putUser(UserRequest userRequest, int id);

    boolean putUserPassword(PasswordRequest passwordRequest,int id);


    List<Shop> findLoveByAccount(int id );
    Boolean addOrDeleteUserLove(int id,int shopId) ;

    List<String> findByAccounts(String account);

    List<Address> putUserAddress(int userId,List<Address> addresses);
}
