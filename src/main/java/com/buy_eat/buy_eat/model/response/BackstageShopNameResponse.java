package com.buy_eat.buy_eat.model.response;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.Tab;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackstageShopNameResponse {

    private Integer id;

    private String name;

    private List<Tabs> tabs;

    public BackstageShopNameResponse() {

    }

    public BackstageShopNameResponse(Shop shop) {
        BeanUtils.copyProperties(shop, this);
        tabs = shop.getTabs().stream().map(v -> new Tabs(v)).collect(Collectors.toList());

    }

    @Getter
    @Setter
    public class Tabs {

        private Integer id;

        private String name;

        public Tabs() {

        }

        public Tabs(Tab tab) {
            BeanUtils.copyProperties(tab, this);

        }
    }
}
