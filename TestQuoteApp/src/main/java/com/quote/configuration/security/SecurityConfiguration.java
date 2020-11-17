package com.quote.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true )
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**")
						.antMatchers(HttpMethod.POST,"/account/user");
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		http.httpBasic();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS );

		http.authorizeRequests()
							
								.antMatchers(HttpMethod.PUT,"/update/{name}").hasAnyRole("ADMINISTRATOR","ADMINISTRATOR")
								.antMatchers(HttpMethod.DELETE,"/quote/{name}").hasAnyRole("ADMINISTRATOR","ADMINISTRATOR")
								.antMatchers(HttpMethod.GET,"/quote/**").authenticated()
								.antMatchers(HttpMethod.POST,"/quote/**").authenticated()
								.antMatchers(HttpMethod.PUT,"/quote").authenticated()
								.antMatchers("/quote/**").authenticated()
								.antMatchers(HttpMethod.POST,"/account/user/{login}/role/{role}").hasRole("ADMINISTRATOR")
								.antMatchers(HttpMethod.DELETE,"/account/user/{login}/role/{role}").hasRole("ADMINISTRATOR");
								
	}

}
