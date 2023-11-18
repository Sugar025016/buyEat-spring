package com.buy_eat.buy_eat.service;

import java.util.List;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.AddressData;

public interface IAddressService {
    List<Address> putUserAddress( int userId  ,List<Address> addresses); 
    Address address( AddressData addresses); 
    public void geocodeAddress(String address);
}

