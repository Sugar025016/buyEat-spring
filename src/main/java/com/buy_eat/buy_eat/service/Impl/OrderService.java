package com.buy_eat.buy_eat.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.Cart;
import com.buy_eat.buy_eat.entity.Order;
import com.buy_eat.buy_eat.entity.OrderDetail;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.enums.Status;
import com.buy_eat.buy_eat.model.request.OrderRequest;
import com.buy_eat.buy_eat.model.response.OrderFinishResponse;
import com.buy_eat.buy_eat.model.response.OrderResponse;
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
        Optional<Address> findFirst = user.getAddresses().stream().filter(a -> a.getId() == orderRequest.getAddressId())
                .findFirst();
        Address address = findFirst
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
        List<Cart> carts = user.getCarts();
        Cart orElseThrow2 = carts.stream().findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart is null"));
        Shop shop = orElseThrow2.getProduct().getShop();

        Order order = new Order(orderRequest.getTakeTime(), orderRequest.getRemark(), shop, user, address);
        iOrderRepository.save(order);

        List<OrderDetail> collect = carts.stream().map(v -> new OrderDetail(v, order)

        ).collect(Collectors.toList());

        iOrderDetailRepository.saveAll(collect);

        List<Integer> collect2 = carts.stream().map(v -> v.getId()).collect(Collectors.toList());
        int deleteAll = iCartRepository.deleteAll(collect2);
        return collect2.size() == deleteAll;
    }

    @Override
    @Transactional
    public Page<OrderResponse> getOrder(int userId, Pageable pageable) {

        User user = iUserService.findById(userId);
        Page<Order> orderPage = iOrderRepository.getOrderByUserPage(user, pageable);
        return orderPage.map(OrderResponse::new);
    }

    @Override
    public Page<OrderFinishResponse> getOrderByShop(int userId, int shopId, List<Integer> status, Pageable pageable) {
        System.out.println("------------" + status);
        User user = iUserService.findById(userId);
        Optional<Shop> findAny = user.getShops().stream().filter(v -> v.getId() == shopId).findAny();
        Shop orElseThrow = findAny.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop is null"));
        Page<Order> orderPage = iOrderRepository.getOrderByShopAndStatusIn(orElseThrow, status, pageable);
        return orderPage.map(OrderFinishResponse::new);
    }

    @Override
    @Transactional
    public boolean putOrderByShop(int userId, int shopId, int status, List<Integer> orderIds) {
        User user = iUserService.findById(userId);
        Optional<Shop> findAny = user.getShops().stream().filter(v -> v.getId() == shopId).findAny();
        Shop orElseThrow = findAny.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Shop is null"));
        List<Integer> beforeByStatus = Status.getBeforeByStatus(status);
        List<Order> orders = iOrderRepository.getOrderByShopAndIdInAndStatusIn(orElseThrow, orderIds,
                beforeByStatus);
        orders.forEach(v -> v.setStatus(Status.getStatus(status).getKey()));
        List<Order> orderList = iOrderRepository.saveAll(orders);
        return orders.size() == orderList.size();
    }
}
