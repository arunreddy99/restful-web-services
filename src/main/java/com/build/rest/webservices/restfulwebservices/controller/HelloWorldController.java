package com.build.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.build.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
//	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello World");
	}

	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldParhVariable(@PathVariable String name) {
		return new HelloWorldBean("hello World, "+ name);
	}
}
