package com.buy_eat.buy_eat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.service.Impl.ProductService;

@RestController
@RequestMapping("/api")
public class productController {
    
        @Autowired
    ProductService productService;


    @RequestMapping(path="/products" , method = RequestMethod.GET )
    public List<Product> getShops(){
        System.out.println("有鬼阿..........=.=+， 2");
        return productService.findAll();
    }
    @RequestMapping(path="/product" , method = RequestMethod.GET )
    public Product getShop(@RequestParam int id){
        System.out.println("有鬼阿..........=.=+， 2");
        return productService.getProductById(id);
    }
    @RequestMapping(path="/product3" , method = RequestMethod.GET )
    public void getShop1(){
        System.out.println("有鬼阿..........=.=+， 333333333");
    }
}

