package com.project.controller;

import java.util.Arrays;
import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.Role;
import com.project.model.User;

@Controller
public class LoginController extends BaseController {

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getFacade().findUserByEmail(auth.getName());
		
		modelAndView.addObject("fullName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("user", user);
		
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);

		modelAndView.addObject("roles", getFacade().findAllRole());

		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("roles", getFacade().findAllRole());

		User userExists = getFacade().findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {

			user.setPassword(getFacade().encodePassword(user.getPassword()));

			user.setActive(Boolean.TRUE);

			//String role = user.getRole();
			String role = "USER";
			Role userRole = getFacade().findByRole(role);
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

			getFacade().saveUser(user);

			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("register");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getFacade().findUserByEmail(auth.getName());

		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");

		return modelAndView;
	}

	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public ModelAndView userHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = getFacade().findUserByEmail(auth.getName());

		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("userMessage", "Content Available Only for Users with USER Role");
		modelAndView.setViewName("user/home.xhtml");

		return modelAndView;
	}

}
