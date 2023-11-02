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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.model.request.BackstageProductAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageProductPutRequest;
import com.buy_eat.buy_eat.model.request.SellProductRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "product")
// @Where(clause = "is_delete = false")
public class Product extends BaseEntity {
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

    @Column(name = "is_delete", length = 255, nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isDelete;

    @Column(name = "is_orderable", length = 255, nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isOrderable;

    // @Column(name = "is_sold_out", length = 255, nullable = false,
    // columnDefinition = "BOOLEAN DEFAULT false")
    // private boolean is_sold_out;

    @JsonIgnore
    @JoinColumn(name = "shop_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Shop shop;

    @JsonIgnore
    @JoinColumn(name = "tab_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tab tab;

    @JoinColumn(name = "file_data")
    @OneToOne(cascade = CascadeType.ALL)
    private FileData fileData;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Cart> cart;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", prise=" + prise +
                ", description=" + description +
                '}';
    }

    public void setDelete(boolean isDelete) {
        if (isDelete) {
            this.isDelete = true;
        }
    }

    public void setProduct(BackstageProductPutRequest productPutRequest) {
        BeanUtils.copyProperties(productPutRequest, this);
        this.fileData = null;
    }

    public void setSellProduct(SellProductRequest sellProductRequest) {
        BeanUtils.copyProperties(sellProductRequest, this);
        this.fileData = null;
    }

    public Product(BackstageProductAddRequest productAddRequest) {
        BeanUtils.copyProperties(productAddRequest, this);

    }

    public Product(SellProductRequest sellProductRequest) {
        BeanUtils.copyProperties(sellProductRequest, this);

    }

    
}
