package com.buy_eat.buy_eat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @JsonIgnore
    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    // @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Category> category;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    // @Column(name = "price", nullable = false)
    // private Integer price;

    // @Column(name = "enabled")
    // private boolean enabled;

    // @Column(name="enable")
    // private FileData image;

    // @Column(name = "description", length = 255)
    // private String description;

    // @Column(name = "category", length = 255)
    // private String category;

    // @Column(name = "creat_date")
    // private Date createDate;

    // @Column(name = "disable")
    // private boolean disable=false;

}
