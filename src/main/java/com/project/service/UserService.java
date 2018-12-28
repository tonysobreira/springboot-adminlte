package com.project.service;

import java.util.List;

import com.project.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public User saveUser(User user);

	public List<User> findAllUser();

}