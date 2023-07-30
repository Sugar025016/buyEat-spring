package com.buy_eat.buy_eat.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;
    @Column(name = "address", length = 11, nullable = false)
    private String address;
    @Column(name = "description", length = 512, nullable = false)
    private String description;
    @Column(name = "account", length = 512, nullable = false)
    private String account;
    @Column(name = "password", length = 512, nullable = false)
    private String password;


    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "createTime")
    private Date createTime;


    // @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Column(name = "changeTime")
    private Date changeTime;
    @Column(name = "isDelete", length = 512, nullable = false)
    private boolean isDelete;
    @Column(name = "disable", length = 512, nullable = false )
    private boolean disable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    private List<Product> products;

    //給關聯過來的回傳值
    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name=" + name + 
                ", phone=" + phone + 
                ", address=" + address + 
                ", description=" + description + 
                '}';
    }

}
