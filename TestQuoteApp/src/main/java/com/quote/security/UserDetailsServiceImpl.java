package com.quote.security;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quote.dao.UserAccountRepository;
import com.quote.model.UserAccount;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserAccountRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserAccount userAccount = userRepository.findById(username)
								.orElseThrow(()->new UsernameNotFoundException(username));
		String password = userAccount.getPassword();
		Set<String>roles=userAccount.getRoles()
				.stream()
				.map(r->"ROLE_"+r.toUpperCase())
				.collect(Collectors.toSet());

		return new User(username, password, AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()])));
	}

}
