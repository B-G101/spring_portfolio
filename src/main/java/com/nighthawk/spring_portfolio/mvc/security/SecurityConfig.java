//package com.nighthawk.spring_portfolio.mvc.security;
//import org.springframework.security.core.userdetails.UserDetailsService;

package com.nighthawk.spring_portfolio.mvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import com.nighthawk.spring_portfolio.mvc.jwt.JwtAuthenticationEntryPoint;
import com.nighthawk.spring_portfolio.mvc.jwt.JwtRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/*
* To enable HTTP Security in Spring, extend the WebSecurityConfigurerAdapter. 
*/
@Configuration
@EnableWebSecurity  // Beans to enable basic Web security
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private UserDetailsService personDetailsService;

    @Bean  // Sets up password encoding style
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(personDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
    // Provide security configuration
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// no CSRF
			.csrf().disable()
			// list the requests/endpoints need to be authenticated
			.authorizeRequests()
				//.antMatchers("/mvc/person/update/**", "/mvc/person/delete/**").authenticated()
				//.antMatchers("/api/person/**").authenticated()
				.antMatchers("/mvc/person/delete/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/mvc/person/update/**").hasAnyAuthority("ROLE_ADMIN")
				.antMatchers("/mvc/person/update/**", "/mvc/person/delete/**").authenticated()
				.and()
			.cors().and()	
			// support cors
			.headers()
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-ExposedHeaders", "*", "Authorization"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "Content-Type", "Authorization", "x-csrf-token", "x-csrf-token"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-MaxAge", "600"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST", "GET", "OPTIONS", "HEAD"))
				.addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Origin", "https://deimie.github.io"))
				.and()
			.formLogin()
        .loginPage("/login")
		.permitAll()
        .and()
    .logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/")
						.permitAll()
						.and()
			// make sure we use stateless session; 
			// session won't be used to store user's state.
			.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)           
		;

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}
