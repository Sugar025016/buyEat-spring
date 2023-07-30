package com.buy_eat.buy_eat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.buy_eat.buy_eat.entity.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {

    Category getCategoryById(Integer id);

    // Category getCategoryById(Integer id);
    // @Modifying
    // long  deleteByCategory(Category category);

    @Modifying
    void  deleteById(Integer id);
}
