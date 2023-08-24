package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.PasswordRequest;
import com.buy_eat.buy_eat.model.request.UserPutRequest;
import com.buy_eat.buy_eat.model.request.UserRequest;
import com.buy_eat.buy_eat.model.response.BackstageUserResponse;
import com.buy_eat.buy_eat.repository.IShopRepository;
import com.buy_eat.buy_eat.repository.IUserRepository;
import com.buy_eat.buy_eat.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IShopRepository iShopRepository;

    @Override
    public User findById(int id) {
        Optional<User> findById = iUserRepository.findById(id);
        if (!findById.isPresent()) {

            throw new NullPointerException();
        }
        return findById.get();
    }


    //     @Override
    // public User findById(int  id) {
    //     Optional<User> findById = iUserRepository.findById(id);

    //     if (!findById.isPresent()) {
    //         throw new NullPointerException();
    //     }
    //     return findById.get();
    // }

    @Override
    public User findByAccount(String account) {
        Optional<User> findById = iUserRepository.findByAccount(account);

        if (!findById.isPresent()) {
            throw new NullPointerException();
        }
        return findById.get();
    }


    @Override
    public Page<BackstageUserResponse> findByName(Pageable pageable, String name) {
        return iUserRepository.findUsersByName(name, pageable);

    }

    @Override
    public boolean addUser(UserRequest userRequest) {
        User user = new User(userRequest);
        iUserRepository.save(user);
        return true;
    }

    @Override
    public boolean existByAccount(String account) {

        return iUserRepository.existByAccount(account);
    }

    

    @Override
    public boolean putUser(UserPutRequest userPutRequest, int id) {
        Optional<User> findById = iUserRepository.findById(id);
        if (!findById.isPresent()) {
            throw new NullPointerException();
        }
        User user = findById.get();

        user.setUser(userPutRequest);
        iUserRepository.save(user);

        return true;
    }

    @Override
    public boolean putUserPassword(PasswordRequest userPutRequest, int id) {
        Optional<User> findById = iUserRepository.findById(id);
        User user = findById.orElseThrow(
                () -> new IllegalArgumentException("Value not found"));

        if (!user.getPassword().equals(userPutRequest.getPassword())) {
            throw new BadCredentialsException("密碼錯誤");
        }

        user.setPassword(userPutRequest.getNewPassword());
        iUserRepository.save(user);

        return true;
    }

    @Override
    public List<Shop> findLoveByAccount(int id) {
        Optional<User> findById = iUserRepository.findById(id);
        User user = findById.orElseThrow(
                () -> new IllegalArgumentException("Value not found"));
                List<Shop> shopLoveList = user.getLoves();
        return shopLoveList;

    }

    @Transient
    @Override
    public List<Shop> addOrDeleteUserLove(int id, int shopId) {
        Optional<User> findById = iUserRepository.findById(id);
        User user = findById.orElseThrow(
                () -> new IllegalArgumentException("Value not found"));
        Optional<Shop> findByIdAndDeleteIsFalse = iShopRepository.findByIdAndIsDeleteIsFalse(shopId);
        Shop shop = findByIdAndDeleteIsFalse.orElseThrow(
                () -> new IllegalArgumentException("Value not found"));
        boolean anyMatch = user.getLoves().stream().anyMatch(v -> v.equals(shop));
        if (anyMatch) {
            user.getLoves().remove(shop);
        } else {
            user.getLoves().add(shop);
        }

        user=iUserRepository.save(user);

        return user.getLoves();
    }

}
