package com.tweetapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.tweetapp.security.CustomUserDetailUserService;
import com.tweetapp.security.JwtAuthenticationEntryPoint;
import com.tweetapp.security.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailUserService customUser;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/api/v1.0/tweets/**").permitAll()
		.antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.PUT).permitAll().antMatchers(HttpMethod.POST).permitAll().antMatchers(HttpMethod.DELETE).permitAll()
		.anyRequest().authenticated().and()
		.exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.customUser).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//BCryptPasswordEncoder()
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public FilterRegistrationBean coresFilter() {
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		CorsConfiguration cors=new CorsConfiguration();
		cors.setAllowCredentials(true);
		cors.addAllowedOriginPattern("*");
		cors.addAllowedHeader("Authorization");
		cors.addAllowedHeader("Content-Type");
		cors.addAllowedHeader("Accept");
		cors.addAllowedMethod("POST");
		cors.addAllowedMethod("GET");
		cors.addAllowedMethod("DELETE");
		cors.addAllowedMethod("PUT");
		cors.addAllowedMethod("OPTIONS");
		cors.setMaxAge(3600L);
		source.registerCorsConfiguration("/**", cors);
		FilterRegistrationBean bean =new FilterRegistrationBean(new CorsFilter(source));
		return bean;
	}

}
