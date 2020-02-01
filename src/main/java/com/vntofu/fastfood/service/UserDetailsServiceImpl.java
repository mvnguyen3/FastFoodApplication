package com.vntofu.fastfood.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vntofu.fastfood.domain.Role;
import com.vntofu.fastfood.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		System.out.println("@UserDetailsServiceImpl.loadUserByUsername(String name).........name: "+ name);
		com.vntofu.fastfood.domain.User user = userRepository.findByUserName(name);
		Set<GrantedAuthority> vntofuGa = new HashSet<>();
		
		vntofuGa.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
				
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), vntofuGa);
	}
	
	
	
}
