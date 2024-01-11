package com.build.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.build.rest.webservices.restfulwebservices.bean.User;
import com.build.rest.webservices.restfulwebservices.dao.UserDaoService;

@RestController
public class UserController {
	
	private UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping(path="/users")
	public List<User> retriveAllUsers(){
		
		return service.findAll();
	}
	
	@GetMapping(path="/users/{id}")
	public User retriveUsersById(@PathVariable int id){
		
		return service.findById(id);
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		
		User savedUser = service.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
