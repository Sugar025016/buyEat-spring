package com.buy_eat.buy_eat.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.buy_eat.buy_eat.entity.User;
import com.buy_eat.buy_eat.repository.IUserRepository;

@Component
@Transactional
public class CustomAuthenticationProvider implements AuthenticationProvider {
	// PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// @Autowired
	// private PasswordEncoder passwordEncoder;
	@Autowired
	IUserRepository iUserRepository;
	private User user;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		// 如果驗證成功，返回一個包含用戶角色的Authentication對象
		Optional<User> findByAccount = iUserRepository.findByAccount(username);
		if (findByAccount.isPresent()) {
			user = findByAccount.get();
			// if (!passwordEncoder.matches(password, user.getPassword()))
			if (!password.equals(user.getPassword()))
				throw new AuthenticationServiceException(String.format("please check account or password"));
		} else {
			throw new BadCredentialsException("please check account or password");

		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (user.getRole() == "admin") {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		}
		return new UsernamePasswordAuthenticationToken(username, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// 返回true表示支持對該類型的Authentication進行驗證
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
