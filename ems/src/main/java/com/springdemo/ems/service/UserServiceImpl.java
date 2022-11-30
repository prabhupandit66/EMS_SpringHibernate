package com.springdemo.ems.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springdemo.ems.dto.UserRegistration;
import com.springdemo.ems.entity.Role;
import com.springdemo.ems.entity.User;
import com.springdemo.ems.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private UserRepository userRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistration userReg) {
		User user = new User(userReg.getFirstName(), userReg.getLastName(),userReg.getEmail(), passwordEncoder.encode(userReg.getPassword()), 
				Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid UserName or Password");
		} 
		return new org.springframework.security.core.userdetails.User(user.getemail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	

}
