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
import com.buy_eat.buy_eat.entity.Address;
import com.buy_eat.buy_eat.entity.Shop;
import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.model.request.PasswordRequest;
import com.buy_eat.buy_eat.model.request.UserPutRequest;
import com.buy_eat.buy_eat.model.response.ShopResponse;
import com.buy_eat.buy_eat.model.response.UserResponse;
import com.buy_eat.buy_eat.repository.IAddressDataRepository;
import com.buy_eat.buy_eat.service.Impl.AddressService;
import com.buy_eat.buy_eat.service.Impl.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    IAddressDataRepository addressDataRepository;

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
    public ResponseEntity<List<ShopResponse>> getLoves(
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        List<Shop> findLoveByAccount = userService.findLoveByAccount(customUserDetails.getId());
        List<ShopResponse> collect = findLoveByAccount.stream().map(v -> new ShopResponse(v))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @RequestMapping(path = "/favorite/{shopId}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> addOrDeleteUserLove(@PathVariable int shopId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Boolean findLoveByAccount = userService.addOrDeleteUserLove(customUserDetails.getId(),
                shopId);
        return ResponseEntity.ok().body(findLoveByAccount);
    }

    @RequestMapping(path = "/address", method = RequestMethod.GET)
    public ResponseEntity<List<Address>> getAddress(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        User findByAccount = userService.findById(customUserDetails.getId());
        return ResponseEntity.ok().body(findByAccount.getAddresses());
    }

    @RequestMapping(path = "/address", method = RequestMethod.PUT)
    public ResponseEntity<List<Address>> putAddress(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody List<Address> addresses) {
        return ResponseEntity.ok().body(userService.putUserAddress(customUserDetails.getId(), addresses));
    }

    // 新增database 的 addressData
    // @RequestMapping(path = "/address", method = RequestMethod.POST)
    // public ResponseEntity<List<AddressData>> Address(@AuthenticationPrincipal
    // CustomUserDetails customUserDetails,
    // @RequestBody List<AddressDataRequest> addressDataRequests) {
    // ArrayList<AddressData> arrayList = new ArrayList<AddressData>();
    // addressDataRequests.stream().forEach(c -> c.getArea().stream().forEach(a -> {
    // List<AddressData> arrayList2 = a.getStreets().stream()
    // .map(s -> new AddressData(s.getStreetKey(), c.getCityName(), a.getAreaName(),
    // s.getStreetName()))
    // .collect(Collectors.toList());
    // arrayList.addAll(arrayList2);
    // }));
    // List<AddressData> saveAll = addressDataRepository.saveAll(arrayList);
    // return ResponseEntity.ok().body(saveAll);
    // }

    @Autowired
    AddressService addressData;

    @RequestMapping(path = "/google", method = RequestMethod.GET)
    public ResponseEntity<String> getGoogle(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody String address) {
                addressData.geocodeAddress(address);
        return ResponseEntity.ok().body("address2");
    }
}
