package com.buy_eat.buy_eat.model.response;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.entity.Tab;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackstageProductResponse {

    private Integer shopId;
    private String shopName;

    private Integer tabId;
    private String tabName;

    private Integer id;
    @JsonProperty("productName")
    private String name;
    private String description;
    private Integer prise;

    private boolean isDelete;
    private boolean isOrderable;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String imgUrl;
    private Integer imgId;

    public BackstageProductResponse(Product product) {
        BeanUtils.copyProperties(product, this);
        Tab tab = product.getTab();
        this.tabId=tab.getId();
        this.tabName=tab.getName();
        this.shopId=tab.getShop().getId();
        this.shopName=tab.getShop().getName();
        if (product.getFileData() != null) {
            this.imgUrl = product.getFileData().getFileName();
            this.imgId = product.getFileData().getId();
        }
    }

}