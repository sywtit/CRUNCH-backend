package com.crunch.crunch_server.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor{
    private static final String HEADER_AUTH = "token";

	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		final String token = request.getHeader(HEADER_AUTH);

		if(token != null && jwtUtil.validateToken(token)){
			return true;
		}else{
			throw new UnauthorizedException();
		}
	}
}
