package com.buy_eat.buy_eat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

   

    Optional<User> findByAccount(String account);
    User getByAccount(String account);

}