package com.buy_eat.buy_eat.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> showLoginForm() {
        // 判斷使用者是否已登入，這裡使用一個假設的方法 isAuthenticated() 來判斷
        System.out.println("/login-----------------------------");
        if (!isAuthenticated()) {

            Map<String, String> responseData = new HashMap<>();
            responseData.put("status", "error");
            responseData.put("message", "請先登入以繼續操作。");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
        }
        // 使用者已登入，返回登入表單
        return ResponseEntity.ok().build();
    }

    private boolean isAuthenticated() {
        // 在這裡實現判斷使用者是否已登入的邏輯，例如使用 Spring Security 的方法進行判斷
        // 此處僅作示範，實際應用需要依賴具體的認證機制
        return false;
    }
}
