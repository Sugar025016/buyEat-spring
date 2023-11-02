package com.buy_eat.buy_eat.model.response;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailResponse {


    
    private Integer id;
    private String productName;
    private int qty;
    private int prise;
    private String remark;
    // private int total;

    // private int totalOriginPrice;

    public OrderDetailResponse(OrderDetail orderDetail) {
        BeanUtils.copyProperties(orderDetail,this);

        this.productName = orderDetail.getProduct().getName();
    }



}
