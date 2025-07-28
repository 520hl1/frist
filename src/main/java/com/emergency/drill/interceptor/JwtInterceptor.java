package com.emergency.drill.interceptor;

import com.emergency.drill.util.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 放行 OPTIONS 请求（关键修改）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("Authorization");
        // 打印原始 Token（包含 Bearer 前缀）
        System.out.println("Raw Token: " + token);
        // 提取纯 Token（去掉 Bearer 前缀）
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            System.out.println("Clean Token: " + token);
        }
        if (token == null || !JwtUtil.verifyToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        // 将用户ID存入request，方便后续接口获取
        request.setAttribute("currentUserId", JwtUtil.getUserId(token));
        request.setAttribute("currentUserRole", JwtUtil.getRole(token));
        return true;
    }
}