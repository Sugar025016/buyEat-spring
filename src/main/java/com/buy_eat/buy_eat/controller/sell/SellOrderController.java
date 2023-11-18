package com.buy_eat.buy_eat.controller.sell;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.config.CustomUserDetails;
import com.buy_eat.buy_eat.enums.Status;
import com.buy_eat.buy_eat.model.response.OrderFinishResponse;
import com.buy_eat.buy_eat.service.Impl.OrderService;

@RestController
@RequestMapping("/sell/order")
public class SellOrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(path = "/{shop}", method = RequestMethod.GET)
    public ResponseEntity<Page<OrderFinishResponse>> getOrderByShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable int shop, @PageableDefault(page = 0, size = 5) Pageable pageable,
            @RequestParam int classify) {
        Sort sort = Sort.by(Sort.Direction.ASC, "takeTime"); // 使用 "takeTime" 欄位進行升序排序

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        List<Integer> keyByClassify = Status.getKeyByClassify(classify);

        return ResponseEntity.ok()
                .body(orderService.getOrderByShop(customUserDetails.getId(), shop, keyByClassify, pageRequest));
    }

    @RequestMapping(path = "/{shop}/{status}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> putOrderByShop(
            @AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable int shop,
            @PathVariable @Valid @Min(12) int status, @RequestBody List<Integer> orderIds) {
        if (status < 12) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok()
                .body(orderService.putOrderByShop(customUserDetails.getId(), shop, status, orderIds));
    }

}
