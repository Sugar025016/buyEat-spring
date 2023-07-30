package com.buy_eat.buy_eat.config.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {
        // 自訂登出成功後的處理邏輯，例如返回 JSON 或其他自訂回應
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 建立回應的 JSON 字串
        String jsonResponse = "{ \"status\": \"success\", \"message\": \"登出成功\" }";

        // 將 JSON 字串寫入回應中
        response.getWriter().write(jsonResponse);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
