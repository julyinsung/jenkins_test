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
	
	@RequestMapping("/bye")
	public String bye(@RequestParam(value="name", required=false, defaultValue="Worlds") String name
			,Model model){

		User user = new User();
		user.setName("mike");
		model.addAttribute("user", user);
		return "bye/bye";
		
	}

	@RequestMapping("/work")
	public String work(@RequestParam(value="name", required=false, defaultValue="Worlds") String name
			,Model model){

		User user = new User();
		user.setName("mike");
		model.addAttribute("user", user);
		return "work/work";
	}
	

	@RequestMapping("/run")
	public String run(@RequestParam(value="name", required=false, defaultValue="Worlds") String name
			,Model model){

		User user = new User();
		user.setName("mike");
		model.addAttribute("user", user);
		return "run/run";
	}
}
