package com.ccsip.coap.master.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ccsip.coap.master.service.IUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private Environment env;

	@Autowired
	protected IUserService userService;
	@Autowired
	private WebAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new AuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
				boolean enabled = Boolean.parseBoolean(env.getProperty("security.enabled" , "true" ));
				if(enabled){
					httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
					// STATELESS
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)			
					.and().authorizeRequests()
					.antMatchers("/api/auth", "/user/", "/test/**", "/confdata/**", "/user/login", "/user/register",
							"/alertGroup", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**")
					.permitAll().anyRequest().authenticated();
				}else{
					httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
					// STATELESS
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)			
					.and().authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					// POST请求不拦截（测试期间）
					.antMatchers(HttpMethod.POST, "/**").permitAll()
					.antMatchers(HttpMethod.GET, "/**", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
					.antMatchers(HttpMethod.DELETE, "/**").permitAll()
					.antMatchers(HttpMethod.PUT, "/**").permitAll()
					// static resource
					.antMatchers("/api/auth", "/user/", "/test/**", "/confdata/**", "/user/login", "/user/register",
							"/alertGroup", "/v2/api-docs", "/swagger-resources", "/swagger-resources/**")
					.permitAll().anyRequest().authenticated();
					
				}

		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}
}
