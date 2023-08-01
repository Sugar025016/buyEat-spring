package com.buy_eat.buy_eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.service.Impl.ProductService;

@RestController
@RequestMapping("/api/product")
public class productController {
    
    @Autowired
    ProductService productService;


    @RequestMapping(path="" , method = RequestMethod.GET )
    public List<Product> getProducts(){
        System.out.println("有鬼阿..........=.=+， 2");
        return productService.findAll();
    }
    @RequestMapping(path="/{id}" , method = RequestMethod.GET )
    public Product getProduct(@PathVariable int id){
        System.out.println("有鬼阿..........=.=+， 2");
        return productService.getProductById(id);
    }
    @RequestMapping(path="/{id}" , method = RequestMethod.DELETE )
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return productService.deleteProductById(id)!=null?ResponseEntity.ok().build():ResponseEntity.internalServerError().build();
    }
}

