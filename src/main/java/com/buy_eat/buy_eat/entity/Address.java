package com.buy_eat.buy_eat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import com.buy_eat.buy_eat.model.request.AddressRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "auto_increment")
    @GenericGenerator(name = "auto_increment", strategy = "native")
    @Column(name = "id")
    private Integer id;

    @Column(name = "city")
    private String city;

    @Column(name = "area")
    private String area;

    @Column(name = "detail")
    private String detail;


    
    public Address(AddressRequest addressRequest) {
        BeanUtils.copyProperties(addressRequest ,this);
    }


    public void setAddress(AddressRequest addressRequest) {
        BeanUtils.copyProperties(addressRequest ,this);
    }

}
