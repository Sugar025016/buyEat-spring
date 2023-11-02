package com.buy_eat.buy_eat.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "product_id")
    @OneToOne(cascade = CascadeType.DETACH)
    private Product product;

    @JoinColumn(name = "ordered_id")
    @ManyToOne(cascade = CascadeType.DETACH)
    private Order order;

    @Column(name = "order_username",nullable = true)
    private String orderUsername;

    @Column(name = "qty",nullable = true)
    private int qty;

    @Column(name = "prise",nullable = false)
    private int prise;

    @Column(name = "remark",nullable = true)
    private String remark;

    public OrderDetail(Product product, Order order, String department, String orderName, int qty, int prise,
            String remark) {

    }
    public OrderDetail(Cart cart) {
        BeanUtils.copyProperties(cart,this);
        this.prise=cart.getProduct().getPrise();
    }
    
}
