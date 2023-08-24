package com.buy_eat.buy_eat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Category;
import com.buy_eat.buy_eat.model.response.CategoryResponse;
import com.buy_eat.buy_eat.service.Impl.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryResponse>> getControllers() {
        List<Category> findAll = categoryService.findAll();
        List<CategoryResponse> collect = findAll.stream().map(v -> new CategoryResponse(v))
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(collect);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryResponse> getController(@PathVariable int id) {
        return ResponseEntity.ok().body(new CategoryResponse(categoryService.getCategoryById(id)));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteController(@PathVariable int id) {
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (!categoryService.deleteById(id)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}