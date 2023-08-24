package com.buy_eat.buy_eat.service.Impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.FileData;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.BackstageShopAddRequest;
import com.buy_eat.buy_eat.model.request.BackstageShopPutRequest;
import com.buy_eat.buy_eat.model.request.ShopRequest;
import com.buy_eat.buy_eat.model.request.ShopSearchRequest;
import com.buy_eat.buy_eat.model.response.BackstageShopResponse;
import com.buy_eat.buy_eat.repository.IAddressRepository;
import com.buy_eat.buy_eat.repository.IFileDateRepository;
import com.buy_eat.buy_eat.repository.IShopRepository;
import com.buy_eat.buy_eat.repository.IUserRepository;
import com.buy_eat.buy_eat.service.IShopService;

@Service
public class ShopService implements IShopService {

    @Autowired
    IShopRepository iShopRepository;
    @Autowired
    IFileDateRepository iFileDateRepository;
    @Autowired
    IAddressRepository iAddressRepository;
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Set<Shop> findShops(ShopSearchRequest shopRequest) {

        return iShopRepository.findByAddress_CityAndAddress_AreaAndCategory_IdAndCategory_name(shopRequest.getCity(),
                shopRequest.getArea(), shopRequest.getCategoryId(), shopRequest.getOther());

    }

    @Override
    public Shop getShopById(int id) {

        Optional<Shop> findById = iShopRepository.findById(id);
        if (!findById.isPresent()) {
            return null;
        }
        return findById.get();
    }

    @Override
    public Page<BackstageShopResponse> findShops(ShopSearchRequest shopRequest, Pageable pageable) {

        return iShopRepository.findByAddress_CityAndAddress_AreaAndCategory_IdAndCategory_name(shopRequest.getCity(),
                shopRequest.getArea(), shopRequest.getCategoryId(), shopRequest.getOther(), pageable);

    }

    @Override
    @Transactional
    public boolean addShop(ShopRequest shopRequest) {
        Shop shop = new Shop(shopRequest);
        iShopRepository.save(shop);
        return true;
    }

    @Transactional
    @Override
    public boolean putShop(BackstageShopPutRequest shopPutRequest) {

        Optional<Shop> findById = iShopRepository.findById(shopPutRequest.getId());
        if (!findById.isPresent()) {
            throw new NullPointerException();
        }
        Optional<Address> findById2 = iAddressRepository.findById(shopPutRequest.getAddress().getId());
        if (!findById2.isPresent()) {
            throw new NullPointerException();
        }

        Optional<FileData> findById3 = iFileDateRepository.findById(shopPutRequest.getImgId());
        if (!findById3.isPresent()) {
            throw new NullPointerException();
        }

        Address address = findById2.get();
        address.setAddress(shopPutRequest.getAddress());
        Address save = iAddressRepository.save(address);
        Shop shop = findById.get();

        shop.setShop(shopPutRequest, save, findById3.get());
        iShopRepository.save(shop);

        return true;

    }

    @Transactional
    @Override
    public boolean addShop(BackstageShopAddRequest shopAddRequest) {

        Optional<FileData> findById3 = iFileDateRepository.findById(shopAddRequest.getImgId());
        if (!findById3.isPresent()) {
            throw new NullPointerException();
        }
        Optional<User> findById = iUserRepository.findByAccount(shopAddRequest.getAccount());
        if (!findById.isPresent()) {
            throw new NullPointerException();
        }

        Address address = new Address(shopAddRequest.getAddress());
        Address save = iAddressRepository.save(address);

        Shop shop = new Shop(shopAddRequest, save, findById3.get(), findById.get());
        iShopRepository.save(shop);

        return true;
    }

    @Override
    public Set<Shop> findShopsLim() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findShopsLim'");
    }

}
