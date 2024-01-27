package com.build.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.build.rest.webservices.restfulwebservices.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
