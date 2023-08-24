package com.buy_eat.buy_eat.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.config.CustomUserDetails;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.PasswordRequest;
import com.buy_eat.buy_eat.model.request.UserPutRequest;
import com.buy_eat.buy_eat.model.response.ShopResponse;
import com.buy_eat.buy_eat.model.response.UserResponse;
import com.buy_eat.buy_eat.service.Impl.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    // CustomUserDetails
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User findByAccount = userService.findById(customUserDetails.getId());
        UserResponse userResponse = new UserResponse(findByAccount);
        return ResponseEntity.ok().body(userResponse);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> putUser(@RequestBody UserPutRequest userPutRequest,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        boolean putUser = userService.putUser(userPutRequest, customUserDetails.getId());
        return ResponseEntity.ok().body(putUser);
    }

    @RequestMapping(path = "/pwd", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> putUserPassword(@RequestBody PasswordRequest passwordRequest,
            @RequestBody UserPutRequest userPutRequest, HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        boolean putUser = userService.putUserPassword(passwordRequest, customUserDetails.getId());

        SecurityContextHolder.clearContext();
        Cookie cookie = new Cookie(authentication.getName(), null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok().body(putUser);
    }

    @RequestMapping(path = "/favorite", method = RequestMethod.GET)
    public ResponseEntity<List<ShopResponse>> getLoves(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Shop> findLoveByAccount = userService.findLoveByAccount(customUserDetails.getId());
        List<ShopResponse> collect = findLoveByAccount.stream().map(v -> new ShopResponse(v))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @RequestMapping(path = "/favorite/{shopId}", method = RequestMethod.PUT)
    public ResponseEntity<List<ShopResponse>> addOrDeleteUserLove(@PathVariable int shopId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Shop> findLoveByAccount = userService.addOrDeleteUserLove(customUserDetails.getId(),
                shopId);
        List<ShopResponse> collect = findLoveByAccount.stream().map(v -> new ShopResponse(v))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

}
