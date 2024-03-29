package com.build.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.build.rest.webservices.restfulwebservices.bean.User;
import com.build.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.build.rest.webservices.restfulwebservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private UserDaoService service;

	public UserController(UserDaoService service) {
		this.service = service;
	}

	@GetMapping(path = "/users")
	public List<User> retriveAllUsers() {

		return service.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public EntityModel<User> retriveUsersById(@PathVariable int id) {

		User user = service.findById(id);

		if (user == null) {
			throw new UserNotFoundException("User not found for id: "+ id);
		}
		EntityModel<User> entityModel= EntityModel.of(user);
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("All-users"));
		return entityModel;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = service.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public void deleteUsersById(@PathVariable int id) {

		service.deleteById(id);

	}
}
