package com.buy_eat.buy_eat.controller.backstage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buy_eat.buy_eat.model.request.UserRequest;
import com.buy_eat.buy_eat.model.response.BackstageUserResponse;
import com.buy_eat.buy_eat.service.Impl.UserService;

@RestController
@RequestMapping("/backstage/user")
public class BackstageUserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<Page<BackstageUserResponse>> getUsers(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(page = 1, size = 1) Pageable pageable) {
        Page<BackstageUserResponse> findByName = userService.findByName(pageable, name);
        return ResponseEntity.ok().body(findByName);
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addUser(
            @RequestBody UserRequest UserRequest) {
        boolean ok = userService.addUser(UserRequest);
        return ResponseEntity.ok().body(ok);
    }

    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public ResponseEntity<Boolean> getUser(
            @RequestParam(value = "account", required = false) String account) {

        return ResponseEntity.ok().body(userService.existByAccount(account));
    }

}
