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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    @Column(name = "isDelete", length = 512, nullable = false)
    private boolean isDelete;
    @Column(name = "disable", length = 512, nullable = false)
    private boolean disable;

    @JoinColumn(name = "file_data")
    @OneToOne(cascade = CascadeType.ALL)
    private FileData fileData;

    @JoinColumn(name = "address")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<User> userLove;

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

}
