package com.buy_eat.buy_eat.model.response;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {


    
    private Integer id;

    private String name;
    private String description;
    private String imgUrl;
    private boolean isOrderable;
    private int prise;

    // private int total;

    // private int totalOriginPrice;

    public ProductResponse(Product product) {
        BeanUtils.copyProperties(product,this);

        if(product.getFileData()!=null){
            this.imgUrl=product.getFileData().getFileName();
        }

    }



}
