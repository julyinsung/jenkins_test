package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.User;

@Controller
public class PipelineController {


	@RequestMapping("/")
	public String hello(@RequestParam(value="name", required=false, defaultValue="Worlds") String name
			,Model model){

		User user = new User();
		user.setName("mike");
		model.addAttribute("user", user);
		return "hello/hello";
		
	}
}
