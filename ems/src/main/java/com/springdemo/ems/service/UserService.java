package com.springdemo.ems.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springdemo.ems.dto.UserRegistration;
import com.springdemo.ems.entity.User;

public interface UserService extends UserDetailsService{
	User save(UserRegistration userReg);
}
