package com.buy_eat.buy_eat.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.entity.Tab;
import com.buy_eat.buy_eat.model.response.TabProductResponse;
import com.buy_eat.buy_eat.service.Impl.TabService;

@RestController
@RequestMapping("/api/tab")
public class TabController {


    @Autowired
    TabService tabService;


    @RequestMapping(path = "/{shopId}", method = RequestMethod.GET)
    public ResponseEntity<List<TabProductResponse>> getTabProducts(@PathVariable int shopId) {
        List<Tab> findTabByShopId = tabService.findTabByShopId(shopId);

        List<TabProductResponse> collect = findTabByShopId.stream().map(v -> new TabProductResponse(v))
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }
    
}
