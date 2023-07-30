package com.buy_eat.buy_eat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Demo {
    @RequestMapping(path="" , method = RequestMethod.POST )
    public String getLogin(){
        System.out.println("有鬼阿..........=.=+，1");

        return "login";
    }
    @RequestMapping(path="" , method = RequestMethod.GET )
    public String getLogin2(){
        System.out.println("有鬼阿..........=.=+， 2");

        return "login";
    }
}
