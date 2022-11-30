package com.springdemo.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springdemo.ems.dto.UserRegistration;
import com.springdemo.ems.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;
	
	@Autowired
	public UserRegistrationController(UserService userService) {
		this.userService = userService;
	}
	
	
	/*@ModelAttribute("user")
	public UserRegistration userRegistration() {
		return new UserRegistration();
	}*/
	
	@GetMapping
	public String showRegistrationForm(Model model) {
		UserRegistration userRegistration = new UserRegistration();
		model.addAttribute("user", userRegistration);
		return "registration";
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("user") UserRegistration userReg) {
		userService.save(userReg);
		return "redirect:/registration?success";
	}
	

}
