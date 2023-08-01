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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "prise", length = 255, nullable = false)
    private int prise;

    @Column(name = "is_delete", length = 255, nullable = false)
    private Boolean isDelete;

    @Column(name = "disable", length = 255, nullable = false)
    private Boolean disable;

    @JsonIgnore
    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Category> category;

    // @JoinColumn(name = "file_data")
    // @OneToOne(cascade = CascadeType.ALL)
    // private FileData fileData;

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
