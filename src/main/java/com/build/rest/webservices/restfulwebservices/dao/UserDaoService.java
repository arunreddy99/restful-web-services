package com.build.rest.webservices.restfulwebservices.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.build.rest.webservices.restfulwebservices.bean.User;

@Component
public class UserDaoService {

	private static List<User> users= new ArrayList<>();
	private static int userCount=0;
	static {
		users.add(new User(++userCount, "Arun", LocalDate.now().minusYears(25)));
		users.add(new User(++userCount, "Ajay", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Vijay", LocalDate.now().minusYears(21)));
	}
	
	public List<User> findAll(){
		
		return users;
	}
	
	public User findById(int id) {
//		for(User user: users) {
//			if(user.getId()==id)
//				return user;
//		}
//		return null;
		
		// functional programming
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().get();
		
	}

	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setId(++userCount);
		users.add(user);
		return user;
	}
	
}
