package com.buy_eat.buy_eat.model.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.Order;
import com.buy_eat.buy_eat.entity.OrderDetail;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderFinishResponse {

    private Integer orderId;
    private String shopName;
    private String userName;
    private String description;
    private String imgUrl;
    private int totalPrise;
    private String remark;
    private String statusChinese;
    private int status;
    private LocalDateTime orderTime;
    private LocalDateTime takeTime;
    private Address address;
    @JsonProperty("orderDetails")
    private List<OrderDetailResponse> orderDetailResponses;

    public OrderFinishResponse(Order order) {
        BeanUtils.copyProperties(order, this);
        Shop shop = order.getShop();
        this.userName = order.getUser().getName();
        this.orderId = order.getId();
        this.shopName = shop.getName();
        this.description = shop.getDescription();
        List<OrderDetail> orderDetail = order.getOrderDetail();
        this.totalPrise = orderDetail.stream().mapToInt(v -> v.getQty() * v.getPrise()).sum();
        this.orderTime = order.getCreateTime();
        this.status = order.getStatus();
        this.statusChinese = Status.getStatus(order.getStatus()).getChinese();
        this.orderDetailResponses = orderDetail.stream().map(v -> new OrderDetailResponse(v))
                .collect(Collectors.toList());
        if (shop.getFileData() != null) {
            this.imgUrl = shop.getFileData().getFileName();
        }
    }

}
