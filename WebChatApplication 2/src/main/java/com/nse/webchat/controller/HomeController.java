package com.nse.webchat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nse.webchat.entity.ChatUser;

@Controller
public class HomeController {

	@Autowired
	HttpSession httpSession;

	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");

		if (httpSession.getAttribute("user") != null)
			mv.setViewName("redirect:/index");

		return mv;
	}

	@GetMapping(value = "/logout")
	public ModelAndView logout() {
		ModelAndView mv = new ModelAndView();
		httpSession.invalidate();
		mv.setViewName("home");
		return mv;
	}

	@GetMapping(value = "/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("home");

		return mv;
	}
	
	@GetMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("index");

		return mv;
		
	}

	@GetMapping("/register")
	public ModelAndView showRegister() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("register");

		return mv;

	}

	@GetMapping("/profile")
	public ModelAndView showProfile() {

		ChatUser user = (ChatUser) httpSession.getAttribute("user");

		ModelAndView mv = new ModelAndView();

		mv.addObject("user", user);
		mv.setViewName("profile");

		return mv;

	}

}
