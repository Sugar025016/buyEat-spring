package com.buy_eat.buy_eat.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.buy_eat.buy_eat.config.handler.CustomAuthenticationFailureHandler;
import com.buy_eat.buy_eat.config.handler.CustomAuthenticationSuccessHandler;
import com.buy_eat.buy_eat.config.handler.CustomLogoutSuccessHandler;
import com.buy_eat.buy_eat.config.handler.JsonAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomAuthenticationProvider customAuthenticationProvider;
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests(requests -> requests
						.antMatchers("/api/**", "/login*").permitAll()
						.antMatchers("/logout*", "/api/upload*").hasRole("USER")
						.antMatchers("/admin/**", "/api/upload*").hasRole("ADMIN")
						.anyRequest().authenticated())
				.formLogin(login -> login
						.loginProcessingUrl("/login")
						.successHandler(new CustomAuthenticationSuccessHandler())
						.failureHandler(new CustomAuthenticationFailureHandler()))
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
						.logoutSuccessHandler(new CustomLogoutSuccessHandler())
						.permitAll()
						.invalidateHttpSession(true)
						.deleteCookies("JSESSIONID")
						.clearAuthentication(true)
						.permitAll())
				.exceptionHandling(handling -> handling
						.authenticationEntryPoint(new JsonAuthenticationEntryPoint()))// 定義判定未登入時回傳JSON
				.rememberMe(me -> me
						.rememberMeCookieName("remember-me")
						.rememberMeParameter("remember-me")
						.tokenRepository(persistentTokenRepository())
						.tokenValiditySeconds(600)
						.userDetailsService(userDetailsService));//定義remember-me等於true 和 token 過期時

		http.csrf(csrf -> csrf
				.ignoringAntMatchers("/login*", "/logout*")
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
		// tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}
}
