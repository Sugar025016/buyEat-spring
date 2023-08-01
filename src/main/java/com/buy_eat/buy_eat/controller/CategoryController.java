package com.buy_eat.buy_eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Category;
import com.buy_eat.buy_eat.service.Impl.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getControllers() {
        System.out.println("有鬼阿..........=.=+， 2");
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getController(@PathVariable int id) {
        System.out.println("有鬼阿..........=.=+， 2");
        return ResponseEntity.ok().body(categoryService.getCategoryById(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteController(@PathVariable int id) {
        System.out.println("有鬼阿..........=.=+， 2");
        if (!categoryService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        if (!categoryService.deleteById(id)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}