package com.build.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.build.rest.webservices.restfulwebservices.bean.Post;
import com.build.rest.webservices.restfulwebservices.bean.User;
import com.build.rest.webservices.restfulwebservices.dao.UserDaoService;
import com.build.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.build.rest.webservices.restfulwebservices.jpa.PostsRepository;
import com.build.rest.webservices.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaController {

	
	private UserRepository userRepository;
	private PostsRepository postRepository;

	public UserJpaController(UserRepository repository, PostsRepository postRepository) {
		this.userRepository= repository;
		this.postRepository= postRepository;
	}

	@GetMapping(path = "/jpa/users")
	public List<User> retriveAllUsers() {

		return userRepository.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<User> retriveUsersById(@PathVariable int id) {

		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found for id: "+ id);
		}
		EntityModel<User> entityModel= EntityModel.of(user.get());
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).retriveAllUsers());
		entityModel.add(link.withRel("All-users"));
		return entityModel;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUsersById(@PathVariable int id) {

		userRepository.deleteById(id);

	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retriveAllPostForUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found for id: "+ id);
		}
		return user.get().getPosts();

	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userRepository.findById(id);

		if (user.isEmpty()) {
			throw new UserNotFoundException("User not found for id: "+ id);
		}
		
		post.setUser(user.get());
		Post savedPost= postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().
				path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
		
	}
}
