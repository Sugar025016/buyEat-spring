package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.Cart;
import com.buy_eat.buy_eat.entity.Order;
import com.buy_eat.buy_eat.entity.OrderDetail;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.OrderRequest;
import com.buy_eat.buy_eat.repository.ICartRepository;
import com.buy_eat.buy_eat.repository.IOrderDetailRepository;
import com.buy_eat.buy_eat.repository.IOrderRepository;
import com.buy_eat.buy_eat.service.ICartService;
import com.buy_eat.buy_eat.service.IOrderService;
import com.buy_eat.buy_eat.service.IShopService;
import com.buy_eat.buy_eat.service.IUserService;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IUserService iUserService;
    @Autowired
    IShopService iShopService;
    @Autowired
    ICartService iCartService;

    @Autowired
    ICartRepository iCartRepository;
    @Autowired
    IOrderRepository iOrderRepository;
    @Autowired
    IOrderDetailRepository iOrderDetailRepository;

    @Override
    @Transactional
    public boolean addOrder(int userId, OrderRequest orderRequest) {

        User user = iUserService.findById(userId);
        Optional<Address> findFirst = user.getAddresses().stream().filter(a->a.getId()==orderRequest.getAddressId()).findFirst();
        Address address = findFirst.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
        List<Cart> carts=user.getCarts();
        Cart orElseThrow2 = carts.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart is null"));
        Shop shop = orElseThrow2.getProduct().getShop();
        

        Order order = new Order( orderRequest.getTakeTime() ,orderRequest.getRemark(),shop,user,address);
        iOrderRepository.save(order);

        List<OrderDetail> collect = carts.stream().map(v->{
            
            OrderDetail orderDetail=new OrderDetail(v);

            return orderDetail;
        }).collect(Collectors.toList());
        iOrderDetailRepository.saveAll(collect);
        
        List<Integer> collect2 = carts.stream().map(v->v.getId()).collect(Collectors.toList());
        int deleteAll = iCartRepository.deleteAll(collect2);

        return collect2.size()==deleteAll;
    }
    
}
