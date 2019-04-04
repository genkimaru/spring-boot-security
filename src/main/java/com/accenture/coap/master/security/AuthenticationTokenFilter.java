package com.accenture.coap.master.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.accenture.coap.master.domain.Token;
import com.accenture.coap.master.domain.User;
import com.accenture.coap.master.service.ITokenService;
import com.accenture.coap.master.service.IUserService;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

	@Autowired
	protected IUserService userService;
	@Autowired
	private ITokenService tokenService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		String authToken = request.getHeader("accessToken");
		logger.info("checking authentication accessToken->" + authToken);
		String username = "username";

		if (authToken != null) {
			Token token = tokenService.findByPublicKey(authToken);
			if (token != null) {
				if (token.getUserId() != null && token.getUserId() != 0) {
					User user = userService.findOneUser(token.getUserId());
					username = user.getName();
					logger.info("checking authentication User->" + username);
					if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
						UserDetails userDetails = this.userService.loadUserByUsername(username);
						if (tokenService.validateToken(authToken, userDetails)) {
							UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
							logger.info("authenticated user " + username + ", setting security context");
							SecurityContextHolder.getContext().setAuthentication(authentication);
						}
					}
				} else {

				}

			}

		}

		chain.doFilter(request, response);
	}
}