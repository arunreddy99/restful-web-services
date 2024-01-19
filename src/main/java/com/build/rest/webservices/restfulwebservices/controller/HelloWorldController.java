package com.build.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.build.rest.webservices.restfulwebservices.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
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
	
	@GetMapping(path="/hello-world-internationalized")
	public String helloWorldInternationalized() {
		Locale locale= LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
//		return "hello World v2";
		
		
	}

}
