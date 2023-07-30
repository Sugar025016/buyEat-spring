package com.buy_eat.buy_eat.service;

import java.util.List;

import com.buy_eat.buy_eat.entity.Category;

public interface ICategoryService {
    List<Category> findAll();    
    Category getCategoryById(int id);
    boolean deleteById(int id); 
    boolean existsById(int id) ;
}
