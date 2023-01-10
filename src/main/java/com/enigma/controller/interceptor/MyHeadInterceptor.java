package com.enigma.controller.interceptor;

import com.enigma.exception.UnauthorizedException;
import com.enigma.utils.JwtUtils;
import com.enigma.utils.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class MyHeadInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getRequestURI().contains(UrlMapping.LOGIN)){
            return true;
        }
        String token = request.getHeader("Authorization");
        System.out.println("TOKEN " + token);
        if (token == null) throw new UnauthorizedException();
        String[] bearerToken = token.split(" ");
        return jwtUtils.validateJwtToken(bearerToken[1]);
    }
}
