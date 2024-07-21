package com.SpringBoot.api.SpringApi.Users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoService {

	private UserRepository repo;

	@Autowired
	public UserDaoService(UserRepository repo) {
		super();
		this.repo = repo;
		
	}
	
	public List<User> findAllUsers (){
		return repo.findAll(); 
	}
	
	public void saveUser (User user) {
		repo.save(user);
	}
	
	public Optional<User> findOne (int id) {
		return repo.findById(id);
	}
	public void RemoveUser (int id) {
		repo.deleteById(id);
	}
}
