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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.model.request.BackstageShopAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageShopPutRequest;
import com.buy_eat.buy_eat.model.request.ShopRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "shop")
public class Shop extends BaseEntity {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;
    @Column(name = "description", length = 512, nullable = false)
    private String description;
    @Column(name = "is_orderable", length = 512, nullable = false, columnDefinition = "VARCHAR(11) DEFAULT false")
    private boolean isOrderable;
    @Column(name = "is_disable", length = 512, nullable = false, columnDefinition = "VARCHAR(11) DEFAULT false")
    private boolean isDisable;
    @Column(name = "is_delete", length = 512, nullable = false, columnDefinition = "VARCHAR(11) DEFAULT false")
    private boolean isDelete;

    @JoinColumn(name = "file_data")
    @OneToOne(cascade = CascadeType.ALL)
    private FileData fileData;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop",fetch = FetchType.LAZY)
    private List<Tab> tabs;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy="loves")
    private List<User> loves;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH ,fetch = FetchType.LAZY)
    @JoinTable(name = "shop_category", joinColumns = @JoinColumn(name = "shop_id"), inverseJoinColumns = @JoinColumn(name = "category_id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "shop_id", "category_id" }))
    private List<Category> category;

    // 給關聯過來的回傳值
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

    public Shop(ShopRequest shopRequest) {
        BeanUtils.copyProperties(shopRequest, this);

    }

    public Shop(BackstageShopAddRequest shopAddRequest, Address address, FileData fileData, User user) {
        BeanUtils.copyProperties(shopAddRequest, this);
        this.address = address;
        this.fileData = fileData;
        this.user = user;
    }

    public void setIsDelete(boolean isDelete, boolean isDisable, boolean isOrderable) {

        this.isOrderable = isOrderable;
        setIsDisable(isDisable);
        setIsDelete(isDelete);
 
    }

    private void setIsDelete(boolean is_delete) {

        this.isDelete = is_delete;

        if (this.isDelete) {
            this.isDisable = true;
            this.isOrderable = true;
        }
    }

    private void setIsDisable(boolean is_disable) {

        this.isDisable = is_disable;

        if (this.isDisable) {
            this.isOrderable = true;
        }
    }

    public void setShop(BackstageShopPutRequest shopPutRequest, Address address, FileData fileData) {

        this.name = shopPutRequest.getShopName();
        this.description = shopPutRequest.getDescription();
        this.phone = shopPutRequest.getPhone();
        this.description = shopPutRequest.getDescription();

        setIsDelete(shopPutRequest.isDelete(), shopPutRequest.isDisable(), shopPutRequest.isOrderable());

        this.fileData = fileData;
        this.address = address;

    }

    public void setShop(BackstageShopPutRequest shopPutRequest, Address address) {
        this.name = shopPutRequest.getShopName();
        this.description = shopPutRequest.getDescription();
        this.phone = shopPutRequest.getPhone();
        this.description = shopPutRequest.getDescription();

        setIsDelete(shopPutRequest.isDelete(), shopPutRequest.isDisable(), shopPutRequest.isOrderable());

        this.address = address;
        this.fileData=null;
    }

}
