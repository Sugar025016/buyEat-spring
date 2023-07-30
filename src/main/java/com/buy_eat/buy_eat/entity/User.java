package com.buy_eat.buy_eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
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
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
    // private List<Product> products;

    //給關聯過來的回傳值
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
