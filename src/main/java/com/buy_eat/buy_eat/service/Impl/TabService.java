package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buy_eat.buy_eat.entity.Product;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.Tab;
import com.buy_eat.buy_eat.model.request.TabProductRequest;
import com.buy_eat.buy_eat.repository.ITabRepository;
import com.buy_eat.buy_eat.service.IShopService;
import com.buy_eat.buy_eat.service.ITabService;
import com.buy_eat.buy_eat.service.IUserService;

@Service
public class TabService implements ITabService {
    @Autowired
    ITabRepository iTabRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    IShopService iShopService;

    @Override
    public Set<Tab> findTabByShopId(int id) {
        
        // return iTabRepository.findByShopIdAndProductsIsOrderableIsTrue(id);
        Set<Tab> findByIsShelveIsTrueAndShopId = iTabRepository.findByIsShelveIsTrueAndShopId(id);
        findByIsShelveIsTrueAndShopId.stream().forEach(t->{
            List<Product> products=t.getProducts().stream().filter(p->p.isOrderable()).collect(Collectors.toList());
            t.setProducts(products);
        }); 
        return findByIsShelveIsTrueAndShopId;
    }

    @Override
    public boolean addTabByShopId(TabProductRequest tabProductRequest, int userId) {
        Shop shopByUserId = iShopService.getShopByUserId( userId,tabProductRequest.getShopId());
        List<Product> products = tabProductRequest.getProductIds().stream().map(pId -> {
            Optional<Product> optional = shopByUserId.getProducts().stream().filter(p -> p.getId() == pId).findFirst();
            Product orElseThrow = optional.orElseThrow(() -> new IllegalArgumentException("Value not found"));
            return orElseThrow;
        }).collect(Collectors.toList());

        Tab tab = new Tab(tabProductRequest.getName(), shopByUserId, tabProductRequest.isShelve(), products);
        Tab save = iTabRepository.save(tab);
        return save != null;
    }

    @Override
    public boolean setTabByShopId(int tabId, TabProductRequest tabProductRequest, int userId) {

        Shop shopByUserId = iShopService.getShopByUserId(tabProductRequest.getShopId(), userId);
        Optional<Tab> findFirst = shopByUserId.getTabs().stream().filter(v -> v.getId() == tabId).findFirst();
        Tab tab = findFirst.orElseThrow(() -> new IllegalArgumentException("Value not found"));

        List<Product> products = tabProductRequest.getProductIds().stream().map(pId -> {
            Optional<Product> optional = shopByUserId.getProducts().stream().filter(p -> p.getId() == pId).findFirst();
            Product orElseThrow = optional.orElseThrow(() -> new IllegalArgumentException("Value not found"));
            return orElseThrow;
        }).collect(Collectors.toList());

        tab.setName(tabProductRequest.getName());
        tab.setShelve(tabProductRequest.isShelve());
        tab.setProducts(products);

        Tab save = iTabRepository.save(tab);
        
        return save != null;
    }

    @Override
    public boolean deleteTab(int tabId, int userId) {

        iTabRepository.getByIdAndShopUserId(tabId,userId).orElseThrow(() -> new IllegalArgumentException("Value not found"));
        iTabRepository.deleteById(tabId);

        return !iTabRepository.findById(tabId).isPresent();
    }

}
