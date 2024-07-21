package com.SpringBoot.api.SpringApi.HelloWorld;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

	
	@RequestMapping("hello")
	public String helloo () {
		
		return "Helloo World";
	}
}
