package com.buy_eat.buy_eat.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.AddressData;
import com.buy_eat.buy_eat.service.IAddressService;
import com.buy_eat.buy_eat.utils.GeocodingService;

@Service
public class AddressService implements IAddressService {


    @Autowired
    GeocodingService geocodingService;

    @Override
    public List<Address> putUserAddress(int userId, List<Address> addresses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putUserAddress'");
    }

    @Override
    public Address address(AddressData addresses) {

        return null;
    }


    @Override
    public void geocodeAddress(String address) {
        System.out.println(address);
        geocodingService.geocodeAddress(address);

    }

}
