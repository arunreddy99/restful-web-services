package com.build.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.rest.webservices.restfulwebservices.bean.Post;

public interface PostsRepository extends JpaRepository<Post, Integer> {

}
