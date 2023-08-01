package com.buy_eat.buy_eat.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "phone", length = 11, nullable = false)
    private String phone;
    @Column(name = "account", length = 11, nullable = false)
    private String account;
    @Column(name = "password", length = 11, nullable = false)
    private String password;
    @Column(name = "role", length = 11, nullable = false)
    private String role;
    @Email 
    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(name = "user_address", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> address;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Shop> shops;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "love", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Shop> shopLoveList;

    // 給關聯過來的回傳值
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", phone=" + phone +
                ", role=" + role +
                '}';
    }


}
